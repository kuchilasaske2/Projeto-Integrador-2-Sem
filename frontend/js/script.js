$(document).ready(function() {
    // Cadastro de usuário
    $("#cadastro-form").on("submit", (event) => {
        event.preventDefault();
        const nome = $("#nome").val();
        const email = $("#email").val();
        const senha = $("#senha").val();
        const body = JSON.stringify({ nome, email, senha });

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/usuarios',
            contentType: 'application/json',
            dataType: 'json',
            data: body,
            success: (res) => {
                $("#message").text("Cadastro realizado com sucesso!").show();
                $("#cadastro-form")[0].reset();
            },
            error: (err) => {
                $("#message").text("Erro ao realizar cadastro.").show();
            }
        });
    });
});