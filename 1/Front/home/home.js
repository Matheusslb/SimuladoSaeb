
window.carregarAlunos = async function() {
    const listaAlunos = document.getElementById('lista-alunos-ul');
    if (!listaAlunos) return;

    listaAlunos.innerHTML = '<li>Aguarde, buscando alunos...</li>';
    
    try {
        const resposta = await fetch(BASE_URL, { headers: { 'Content-Type': 'application/json' } });
        const alunos = await resposta.json();
        
        // Cria a lista de itens
        listaAlunos.innerHTML = alunos.length === 0 
            ? '<li>Nenhum aluno cadastrado.</li>' 
            : alunos.map(aluno => `<li>ID: ${aluno.id} | Email: ${aluno.email}</li>`).join('');
            
        exibirMensagem(`Lista carregada: ${alunos.length} alunos.`);
    } catch (erro) { 
        listaAlunos.innerHTML = '<li>Erro ao buscar a lista.</li>'; 
        exibirMensagem('Falha na comunicação ao buscar a lista de alunos.'); 
    }
};

document.addEventListener('DOMContentLoaded', window.carregarAlunos);