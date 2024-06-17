const url='http://localhost:8080/comment'
let comment;
let token;
let comments = [];

document.onreadystatechange = () => {
    if(document.readyState =="complete"){
        commentGetAll();
    }
}

const commentGetAll = () => {
    token = localStorage.getItem("token");

    $.ajax({
        type: 'GET',
        url: `${url}/all`,
        contentType: 'application/json',
        dataType: 'json',
        headers: { "token": token },
        success: (res) => {
            fillList(res);
        },
        error: (err) => {
            $("#message").text("Erro ao carregar comentários.");
        }
    });
}

const fillList = (comments) => {
    const ul = $("<ul>");

    comments.forEach(e => {
        const li = $("<li>");
        const lbl = $(`<label for=${e.id}>`).append(e.text);
        const btn = $(`<button id='del-${e.id}' onclick='deleteComment(${e.id})'>Excluir</button>`);
        li.append(lbl, btn);
        ul.append(li);
    });

    $("#comment-list").empty();
    $("#comment-list").append(ul);
}

const authenticate =() =>{

    const email = $("#e").val();
    const password = $("#s").val();
    const body = JSON.stringify({ email, senha: password });

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/auth',
        contentType: 'application/json',
        dataType: 'json',
        data: body,
        success: (res) => {
            const { token, nome, email } = res; // Acessa o token, nome e email do usuário da resposta
            localStorage.setItem("token", token);
            localStorage.setItem("nome", nome); // Armazena o nome do usuário
            localStorage.setItem("email", email);
            console.log(token);
            window.location.href = 'index.html'; // Redireciona para a página principal
        },
        error: (err) => {
            console.error("Erro ao autenticar", err);
            alert("Erro ao autenticar. Verifique suas credenciais.");
        }
    });
}

const search = (obj) => {
    const text = $("#addcomment-text").val();
    
    token = localStorage.getItem("token");

    $.ajax({
        type: 'GET',
        url: `${url}/search?q=${text}&s=p`,
        contentType: 'application/jason',
        dataType: 'json',
        headers: {"token": token},
        success: (res) => { fillList(res)}
    });
}

const markToggle = (commentId) =>{
    console.log(commentId);
    const c = comments.find(e => e.id == commentId);
    console.log(c);

    const body = `{"text": "${c.text}",
                    "status": "${(c.status == 'DONE') ? 'PENDING' : 'DONE'}",
                    "notes": "${c.notes}"}`

    $.ajax({
        type: 'PUT',
        url: `${url}/${c.id}`,
        data: body,
        contentType: 'application/json',
        dataType: 'json',
        success: (res) => {commentGetAll();
                            alert('Comentário enviado com sucesso!');
        }
    })
}

const deleteComment = (commentId) => {
    const token = localStorage.getItem("token");

    $.ajax({
        type: 'DELETE',
        url: `${url}/comment/${commentId}`,
        contentType: 'application/json',
        dataType: 'json',
        headers: { "token": token },
        success: (res) => {
            commentGetAll(); // Atualiza a lista de comentários após a exclusão
            $("#message").text("Comentário excluído com sucesso!");
        },
        error: (err) => {
            $("#message").text("Erro ao excluir comentário.");
        }
    });
}

const createComment = (event) => {
    event.preventDefault();
    
    const text = $("#comment-text").val();
    const rating = $("#comment-rating").val(); // Captura a nota de avalação
    const token = localStorage.getItem("token");
    const body = JSON.stringify({ text, rating });

    $.ajax({
        type: 'POST',
        url: `${url}`,
        contentType: 'application/json',
        dataType: 'json',
        headers: { "token": token },
        data: body,
        success: (res) => {
            $("#message").text("Comentário criado com sucesso!");
            $("#create-comment-form")[0].reset();
            commentGetAll(); // Atualiza a lista de comentários
        },
        error: (err) => {
            $("#message").text("Erro ao criar comentário.");
        }
    });
};

const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("name");
    localStorage.removeItem("email");
    window.location.href = 'teste.html'; // Redireciona para a página de login
};

$(document).ready(() => {
    $("#create-comment-form").on("submit", createComment);
});

$(document).ready(function() {
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