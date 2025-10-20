document.addEventListener("DOMContentLoaded", () => {
  document
    .getElementById("log-usuario")
    .addEventListener("submit", async (evento) => {
      evento.preventDefault();

      const email = document.getElementById("log-email").value;
      const senha = document.getElementById("log-senha").value;

      try {
        const reposta = await fetch(`${BASE_URL}/login`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ email: email, senha: senha }),
        });
        if (reposta.status === 200) {
          exibirMensagem("login realizado");
          setTimeout(() => {
            window.location.href = "/home/home.html";
          }, 500);
        } else {
          exibirMensagem("login falho");
        }
      } catch (erro) {
        exibirMensagem("erro de conexao");
      }
    });
});
