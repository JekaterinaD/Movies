document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const movieId = urlParams.get('movieId');

    fetch(`http://localhost:8080/movies/${movieId}`)
        .then(response => response.json())
        .then(detail => {
            // Assuming the response matches the structure of MovieDetailDTO
            document.getElementById('movieTitle').textContent = detail.title || 'N/A';
            document.getElementById('movieYear').textContent = `Year: ${detail.year}` || 'Year: N/A';
            document.getElementById('movieRanking').textContent = `Ranking: ${detail.ranking}` || 'Ranking: N/A';
            document.getElementById('movieGenres').textContent = `Genres: ${detail.genres.join(', ')}` || 'Genres: N/A';

            const rolesList = detail.roles.map(role => `${role.roleName}: ${role.actorFullName} (${role.gender})`).join(', ');
            document.getElementById('movieRoles').textContent = `Roles: ${rolesList}` || 'Roles: N/A';

            const directorNames = detail.directors.map(director => director.fullName).join(', ');
            document.getElementById('movieDirectors').textContent = `Directors: ${directorNames}` || 'Directors: N/A';

            document.getElementById('movieDistributor').textContent = `Distributor: ${detail.distributorName}` || 'Distributor: N/A';
        })
        .catch(error => {
            console.error('Error loading movie details:', error);
        });
});

