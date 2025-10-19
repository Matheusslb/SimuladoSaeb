
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('cadastro-form').addEventListener('submit', async (evento) => {
        evento.preventDefault();
        
        const email = document.getElementById('reg-email').value;
        const senha = document.getElementById('reg-senha').value;

        try {
            const resposta = await fetch(BASE_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id: null, email: email, senha: senha }) 
            });

            if (resposta.status === 201) {
                exibirMensagem('Cadastro realizado com sucesso. Redirecionando...');
                setTimeout(() => { window.location.href = '/Login/login.html'; }, 500);
            } else {
                const dados = await resposta.json();
                exibirMensagem(`Erro no cadastro: ${dados.message || resposta.statusText}`);
            }
        } catch (erro) { 
            exibirMensagem('Erro de conexão com o servidor.'); 
        }
    });
});