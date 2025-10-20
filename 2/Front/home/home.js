window.carregarUsuarios = async function () {
  const lista = document.getElementById("lista");
  if (!lista) return;

  lista.innerHTML = "<li>aguarde a lista carregar<li>";

  try {
    const resposta = await fetch(BASE_URL, {
      headers: { "Content-Type": "application/json" },
    });
    const usuarios = await resposta.json();

    lista.innerHTML =
      usuarios.lenght === 0
        ? "<li>nenhum usu encontrado</li>"
        : usuarios.map((a) => `<li>ID: ${a.id} | email: ${a.email}`).join("");
    exibirMensagem(`Lista carregada: ${usuarios.length} usuario.`);
  } catch (e) {
    lista.innerHTML = "<li>Erro de buscar<li>";
    exibirMensagem("falha na comunicacao");
  }

  document.addEventListener("DOMContentLoaded", window.carregarUsuarios);
};
