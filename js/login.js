const tabs = document.querySelectorAll(".tabs button");
tabs.forEach((tab) => {
    tab.addEventListener("click", () => {
        tabs.forEach((btn) => {
            btn.classList.remove("active");
        });
        tab.classList.add("active");
    });
});
function mostrarSenha() {
    const senha = document.getElementById("senha");

    senha.type =
        senha.type === "password"
        ? "text"
        : "password";
}
