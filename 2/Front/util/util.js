  const BASE_URL = "http://localhost:8080/Usuarios";

  window.exibirMensagem = function (texto) {
    const elementoMensagem = document.getElementById("mensagem");
    if (elementoMensagem) {
      elementoMensagem.textContent = texto;
    }

    window.logout = function () {
      window.location.href = "/login/login.html";
    };
  };
