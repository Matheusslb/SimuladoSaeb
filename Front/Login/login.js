
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('login-form').addEventListener('submit', async (evento) => {
        evento.preventDefault();
        
        
        const email = document.getElementById('log-email').value;
        const senha = document.getElementById('log-senha').value;

        try {
            const resposta = await fetch(`${BASE_URL}/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email: email, senha: senha })
            });
            
            if (resposta.status === 200) {
                exibirMensagem('Login bem-sucedido. Redirecionando...');
                setTimeout(() => { window.location.href = '/home/home.html'; }, 500);
            } else {
                const dados = await resposta.json();
                exibirMensagem(`Falha no login: ${dados.message || resposta.statusText}`);
            }
        } catch (erro) { 
            exibirMensagem('Erro de conexão com o servidor.'); 
        }
    });
});