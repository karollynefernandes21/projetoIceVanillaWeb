
let itensVendaRequest = []; 

// Referências aos elementos DOM
const sorveteSelect = document.getElementById('sorveteSelect');
const quantidadeItemInput = document.getElementById('quantidadeItem');
const adicionarItemButton = document.getElementById('adicionarItemButton');
const itensVendaBody = document.getElementById('itensVendaBody');
const clienteSelect = document.getElementById('clienteSelect');
const clienteNomeDisplay = document.getElementById('clienteNomeDisplay');

const displaySubtotal = document.getElementById('displaySubtotal');
const displayTotal = document.getElementById('displayTotal');
const inputValorTotal = document.getElementById('inputValorTotal'); // Campo oculto para envio

// FUNÇÃO PARA FORMATAR VALORES
const formatarMoeda = (valor) => 
    new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(valor);

// FUNÇÃO PARA ADICIONAR UM ITEM
function adicionarItem() {
    const sorveteId = sorveteSelect.value;
    const quantidade = parseInt(quantidadeItemInput.value);

    if (!sorveteId || quantidade <= 0) {
        alert('Selecione um sorvete e insira uma quantidade válida.');
        return;
    }
    
    const novoItem = {
        sorveteId: parseInt(sorveteId),
        quantidade: quantidade
    };
    
    // Evita itens duplicados e só adiciona a quantidade
    const itemExistente = itensVendaRequest.find(item => item.sorveteId === novoItem.sorveteId);

    if (itemExistente) {
        itemExistente.quantidade += novoItem.quantidade;
    } else {
        itensVendaRequest.push(novoItem);
    }
    
    // Aciona o cálculo no backend
    calcularTotaisNoBackend();
}

// FUNÇÃO PARA ATUALIZAR O NOME DO CLIENTE
function atualizarNomeClienteDisplay() {
    // Acessa a opção que está selecionada no momento
    const selectedOption = clienteSelect.options[clienteSelect.selectedIndex];
    
    // Obtém o texto (nome do cliente)
    const nomeCliente = selectedOption.text;

    // Se o valor for vazio será exibido: "Nenhum"
    if (clienteSelect.value === "") {
        clienteNomeDisplay.textContent = 'Nenhum';
    } else {
        // Caso contrário, exibe o nome do cliente
        clienteNomeDisplay.textContent = nomeCliente;
    }
}

// FUNÇÃO QUE CALCULA OS TOTAIS NO BACKEND
function calcularTotaisNoBackend() {
    
    fetch('/api/vendas/calcular-totais', { 
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(itensVendaRequest)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Erro de rede ou servidor: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        // data é o CalculoVendaResponseDTO
        
        atualizarDisplays(data.valorTotal);
        renderizarItensTabela(data.itens); 
    })
    .catch(error => {
        console.error('Erro ao calcular totais:', error);
        alert('Erro ao calcular totais.');
    });
}

// FUNÇÃO PARA ATUALIZAR OS DISPLAYS DE TOTAL
function atualizarDisplays(total) {

    displaySubtotal.textContent = formatarMoeda(total);
    displayTotal.textContent = formatarMoeda(total);
    
    // Atualiza o campo oculto para envio do formulário ("valorTotal")
    inputValorTotal.value = total; 
}

// FUNÇÃO PARA RENDERIZAR A TABELA
function renderizarItensTabela(itensCalculados) {
    itensVendaBody.innerHTML = ''; 

    if (itensCalculados.length === 0) {
        itensVendaBody.innerHTML = `<tr><td colspan="5" class="text-center">Nenhum item adicionado.</td></tr>`;
        return;
    }

    itensCalculados.forEach((item) => {
        const row = `
            <tr>
                <td>${item.nomeSorvete}</td>
                <td><input type="number" value="${item.quantidade}" min="1" class="form-control form-control-sm item-quantidade" 
                           data-sorvete-id="${item.sorveteId}" style="width: 70px;"/></td>
                <td>${formatarMoeda(item.precoUnitario)}</td>
                <td>${formatarMoeda(item.subtotal)}</td>
                <td>
                    <button type="button" class="btn-lixeira" aria-label="Remover item" onclick="removerItem(${item.sorveteId})">
                        &#x1F5D1;
                    </button>
                </td>
            </tr>
        `;
        itensVendaBody.innerHTML += row;
    });
    
    // Listener para alterações de quantidade
    document.querySelectorAll('.item-quantidade').forEach(input => {
        input.addEventListener('change', atualizarQuantidadeNaTabela);
    });
}

// ---- FUNÇÕES DE MANIPULAÇÃO DE ITENS ----

// FUNÇÃO PARA REMOVER UM ITEM
function removerItem(sorveteId) {
    itensVendaRequest = itensVendaRequest.filter(item => item.sorveteId !== sorveteId);
    calcularTotaisNoBackend(); 
}

// FUNÇÃO PARA ATUALIZAR QUANTIDADE NA TABELA
function atualizarQuantidadeNaTabela(event) {
    const input = event.target;
    const sorveteId = parseInt(input.getAttribute('data-sorvete-id'));
    const novaQuantidade = parseInt(input.value);

    const item = itensVendaRequest.find(i => i.sorveteId === sorveteId);

    if (item && novaQuantidade > 0) {
        item.quantidade = novaQuantidade;
        calcularTotaisNoBackend(); 
    } else if (item && novaQuantidade <= 0) {
         removerItem(sorveteId);
    }
}

// FUNÇÃO DE PREPARAÇÃO DE ENVIO DE FORMULÁRIO
function prepararFormularioParaSubmissao() {
    const form = document.querySelector('form');
    
    // Remove inputs ocultos antigos de itens
    form.querySelectorAll('.hidden-item-input').forEach(input => input.remove());

    // Cria inputs ocultos para cada item no formato DTO
    itensVendaRequest.forEach((item, index) => {
        
        // Input para o ID do sorvete
        const idInput = document.createElement('input');
        idInput.type = 'hidden';
        idInput.name = `itens[${index}].sorveteId`;
        idInput.value = item.sorveteId;
        idInput.classList.add('hidden-item-input');
        form.appendChild(idInput);

        // Input para a quantidade
        const qtdInput = document.createElement('input');
        qtdInput.type = 'hidden';
        qtdInput.name = `itens[${index}].quantidade`;
        qtdInput.value = item.quantidade;
        qtdInput.classList.add('hidden-item-input');
        form.appendChild(qtdInput);
    });
}

// Listener para adicionar um item
if (adicionarItemButton) {
    adicionarItemButton.addEventListener('click', adicionarItem);
}

if (clienteSelect && clienteNomeDisplay) {
    
    clienteSelect.addEventListener('change', atualizarNomeClienteDisplay);
    
    // Chamada inicial do método
    atualizarNomeClienteDisplay(); 
}

document.querySelector('form').addEventListener('submit', function(event) {
    
    // Preenche os inputs ocultos antes de enviar.
    prepararFormularioParaSubmissao();
    
    // Validação final
    if (itensVendaRequest.length === 0) {
        alert("Adicione pelo menos um item à venda antes de finalizar.");
        event.preventDefault(); 
    }
});

// ---- INICIALIZAÇÃO ----

document.addEventListener('DOMContentLoaded', () => {
    
    // Garante que os displays comecem em R$ 0,00
    calcularTotaisNoBackend(); 
});
