$(document).ready(function() {
    // Handle registration
    $('#registerForm').on('submit', function(event) {
        event.preventDefault();

        var userData = {
            name: $('#name').val(),
            email: $('#email').val(),
            address: $('#address').val(),
            password: $('#password').val(),
            preferences: $('#preferences').val(),
            radius: $('#radius').val()
        };

        $.ajax({
            type: 'POST',
            url: '/auth/register',
            contentType: 'application/json',
            data: JSON.stringify(userData),
            success: function() {
                window.location.href = 'login.html';
            },
            error: function() {
                alert('Registration failed.');
            }
        });
    });

    // Handle login
    $('#loginForm').on('submit', function(event) {
        event.preventDefault();

        var loginData = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        $.ajax({
            type: 'POST',
            url: '/auth/login',
            contentType: 'application/json',
            data: JSON.stringify(loginData),
            success: function() {
                window.location.href = 'home.html';
            },
            error: function() {
                alert('Login failed.');
            }
        });
    });

    // Load user data on home page
    if (window.location.pathname === '/home.html') {
        $.get('/user/profile', function(user) {
            $('#userName').text(user.name);
            loadRestaurants(user.preferences, user.radius);
        });
    }

    function loadRestaurants(preferences, radius) {
        $.get('/restaurants?type=' + preferences, function(restaurants) {
            var filteredRestaurants = restaurants.filter(function(restaurant) {
                return getDistanceFromLatLonInKm(userLatitude, userLongitude, restaurant.latitude, restaurant.longitude) <= radius;
            });

            var restaurantList = '';
            filteredRestaurants.forEach(function(restaurant) {
                restaurantList += '<div>' + restaurant.name + ' (' + restaurant.type + ')</div>';
            });

            $('#restaurants').html(restaurantList);
        });
    }

    // Calculate distance between two coordinates
    function getDistanceFromLatLonInKm(lat1, lon1, lat2, lon2) {
        var R = 6371; // Radius of the earth in km
        var dLat = deg2rad(lat2 - lat1);
        var dLon = deg2rad(lon2 - lon1);
        var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var d = R * c; // Distance in km
        return d;
    }

    function deg2rad(deg) {
        return deg * (Math.PI / 180);
    }
});