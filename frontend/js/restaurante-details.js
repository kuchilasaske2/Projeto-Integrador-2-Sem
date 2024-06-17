const urlParams = new URLSearchParams(window.location.search);
const restaurantId = urlParams.get('id');

const restaurants = [
    { id: 1, name: "Restaurante 1", description: "Descrição do Restaurante 1", image: "assets/restaurante1.jpg" },
    { id: 2, name: "Restaurante 2", description: "Descrição do Restaurante 2", image: "assets/restaurante2.jpg" },
    // Adicione mais restaurantes conforme necessário
];

const fetchRestaurantDetails = (id) => {
    const restaurant = restaurants.find(r => r.id == id);
    if (restaurant) {
        $("#restaurant-name").text(restaurant.name);
        $("#restaurant-description").text(restaurant.description);
        $("#restaurant-image").attr("src", restaurant.image);
    } else {
        $("#message").text("Restaurante não encontrado.");
    }
};

const submitExperience = (event) => {
    event.preventDefault();
    
    const text = $("#experience-text").val();
    const rating = $("#experience-rating").val();
    const token = localStorage.getItem("token");
    const body = JSON.stringify({ text, rating, restaurantId });

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/experiences',
        contentType: 'application/json',
        dataType: 'json',
        headers: { "token": token },
        data: body,
        success: (res) => {
            $("#message").text("Experiência enviada com sucesso!");
            $("#experience-form")[0].reset();
        },
        error: (err) => {
            $("#message").text("Erro ao enviar experiência.");
        }
    });
};

$(document).ready(() => {
    fetchRestaurantDetails(restaurantId);
    $("#experience-form").on("submit", submitExperience);
});
