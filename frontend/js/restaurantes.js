const restaurants = [
    { id: 1, name: "Restaurante 1", description: "Descrição do Restaurante 1", image: "assets/restaurante1.jpg" },
    { id: 2, name: "Restaurante 2", description: "Descrição do Restaurante 2", image: "assets/restaurante2.jpg" },
    // Adicione mais restaurantes conforme necessário
];

const renderRestaurants = () => {
    const list = $("#restaurant-list");
    list.empty();
    restaurants.forEach(restaurant => {
        const item = $(`
            <div class="col-md-4">
                <div class="card">
                    <img src="${restaurant.image}" class="card-img-top" alt="${restaurant.name}">
                    <div class="card-body">
                        <h5 class="card-title">${restaurant.name}</h5>
                        <p class="card-text">${restaurant.description}</p>
                        <button class="btn btn-primary" onclick="window.location.href='restaurantes-details.html?id=${restaurant.id}'">Ver Detalhes</button>
                    </div>
                </div>
            </div>
        `);
        list.append(item);
    });
};

$(document).ready(() => {
    renderRestaurants();
});
