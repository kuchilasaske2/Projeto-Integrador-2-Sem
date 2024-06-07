$(document).ready(function() {
    // Cadastro de usuário
    $('#cadastroForm').on('submit', function(e) {
        e.preventDefault();
        var usuario = {
            nome: $('#nome').val(),
            email: $('#email').val(),
            senha: $('#senha').val()
        };
        $.ajax({
            url: 'http://localhost:8080/api/usuarios',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(usuario),
            success: function(response) {
                alert('Usuário cadastrado com sucesso!');
                window.location.href = 'index.html';
            },
            error: function(error) {
                alert('Erro ao cadastrar usuário!');
            }
        });
    });

    // Login de usuário
    $('#loginForm').on('submit', function(e) {
        e.preventDefault();
        var email = $('#email').val();
        var senha = $('#senha').val();
        $.ajax({
            url: 'http://localhost:8080/api/login',
            type: 'POST',
            contentType: 'application/json',
            success: function(usuarios) {
                var usuario = usuarios.find(u => u.email === email && u.senha === senha);
                if (usuario) {
                    localStorage.setItem('loggedInUsuario', JSON.stringify(usuario));
                    alert('Login realizado com sucesso!');
                    window.location.href = 'index.html';
                    showUserMenu();
                } else {
                    alert('Credenciais inválidas!');
                }
            },
            error: function(error) {
                alert('Erro ao realizar login!');
            }
        });
    });

    // Mostrar menu do usuário logado
    function showUserMenu() {
        var loggedInUsuario = localStorage.getItem('loggedInUsuario');
        if (loggedInUsuario) {
            $('#menu').hide();
            $('#userMenu').show();
        }
    }

    // Exclusão de usuário
    window.deleteUser = function() {
        var loggedInUsuario = JSON.parse(localStorage.getItem('loggedInUsuario'));
        if (loggedInUsuario) {
            $.ajax({
                url: 'http://localhost:8080/api/usuarios/' + loggedInUsuario.id,
                type: 'DELETE',
                success: function(response) {
                    alert('Conta excluída com sucesso!');
                    localStorage.removeItem('loggedInUsuario');
                    window.location.href = 'index.html';
                    $('#menu').show();
                    $('#userMenu').hide();
                },
                error: function(error) {
                    alert('Erro ao excluir conta!');
                }
            });
        }
    };

    // Carregar dados do usuário no formulário de atualização
    if (window.location.pathname.endsWith('update.html')) {
        var loggedInUsuario = JSON.parse(localStorage.getItem('loggedInUsuario'));
        if (loggedInUsuario) {
            $('#nome').val(loggedInUsuario.nome);
            $('#email').val(loggedInUsuario.email);
            $('#senha').val(loggedInUsuario.senha);
        }

        $('#updateForm').on('submit', function(e) {
            e.preventDefault();
            var updatedUser = {
                name: $('#nome').val(),
                email: $('#email').val(),
                password: $('#senha').val()
            };
            $.ajax({
                url: 'http://localhost:8080/api/usuarios/' + loggedInUser.id,
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(updatedUser),
                success: function(response) {
                    alert('Dados atualizados com sucesso!');
                    localStorage.setItem('loggedInUser', JSON.stringify(response));
                    window.location.href = 'index.html';
                },
                error: function(error) {
                    alert('Erro ao atualizar dados!');
                }
            });
        });
    }
    // Alternar tema
    $('#themeToggle').on('click', function() {
        $('body').toggleClass('dark');
        var theme = $('body').hasClass('dark') ? 'dark' : 'light';
        localStorage.setItem('theme', theme);
    });

    // Aplicar tema salvo
    var savedTheme = localStorage.getItem('theme');
    if (savedTheme) {
        $('body').addClass(savedTheme);
    }

    // Mostrar menus apropriados
    var userId = localStorage.getItem('userId');
    if (userId) {
        $('#menu').hide();
        $('#userMenu').show();
    } else {
        $('#menu').show();
        $('#userMenu').hide();
    }
});