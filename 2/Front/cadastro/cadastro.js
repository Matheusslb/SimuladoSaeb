document.addEventListener("DOMContentLoaded", () => {
  document
    .getElementById("cadas-usuario")
    .addEventListener("submit", async (evento) => {
      evento.preventDefault();

      const email = document.getElementById("cadas-email").value;
      const senha = document.getElementById("cadas-senha").value;

      try {
        const reposta = await fetch(BASE_URL, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ id: null, email: email, senha: senha }),
        });
        if (reposta.status === 201) {
          exibirMensagem("cadastro realizado");
          setTimeout(() => {
            window.location.href = "/login/login.html";
          }, 500);
        } else {
          exibirMensagem("cadastro falho");
        }
      } catch (erro) {
        exibirMensagem("erro de conexao");
      }
    });
});
