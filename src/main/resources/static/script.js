document.addEventListener("DOMContentLoaded", function () {
    fetch('http://localhost:8080/movies/years')
        .then(response => response.json())
        .then(years => {
            const container = document.getElementById('years');
            years.forEach(year => {
                const yearSpan = document.createElement('span');
                yearSpan.textContent = year;
                yearSpan.classList.add('year');
                yearSpan.addEventListener('click', () => loadMovies(year));
                container.appendChild(yearSpan);
                container.appendChild(document.createTextNode(' ')); // Adds a space
            });
        })
        .catch(error => console.error('Error:', error));
});

function loadDistributor(distributorId) {
    fetch(`http://localhost:8080/distributors/${distributorId}`)
        .then(response => response.json())
        .then(distributor => {
            console.log(distributor);
        })
        .catch(error => console.error('Error loading distributor:', error));
}

function loadMovies(year) {
    fetch(`http://localhost:8080/movies/year/${year}`)
        .then(response => response.json())
        .then(movies => {
            const table = document.getElementById('movieTable');
            const tableBody = document.getElementById('movieTableBody');
            tableBody.innerHTML = '';

            movies.forEach(movie => {
                const row = tableBody.insertRow();

                row.insertCell(0).textContent = movie.year || 'N/A';
                row.insertCell(1).textContent = movie.name || 'N/A';
                row.insertCell(2).textContent = movie.ranking || 'N/A';
                row.insertCell(3).textContent = movie.distributorName;
                row.insertCell(4).innerHTML = '<a href="details.html?movieId=' + movie.id + '">Detail</a>';
                row.insertCell(5).innerHTML = '<button onclick="changeRanking(' + movie.id + ')">Change ranking</button>';
                row.insertCell(6).innerHTML = '<a href="#" onclick="showComments(' + movie.id + '); return false;">Comments</a>';
            });
            table.style.display = movies.length > 0 ? 'table' : 'none';
        })
        .catch(error => {
            console.error('Error loading movies:', error);
            document.getElementById('movieTable').style.display = 'none';
        });
}

function changeRanking(movieId) {
    const newRanking = prompt("Enter new ranking:");
    if (newRanking !== null) {
        const parsedRanking = parseFloat(newRanking);
        if (!isNaN(parsedRanking) && parsedRanking >= 0 && parsedRanking <= 10) {
            const rankingUpdateDTO = {
                newRanking: parsedRanking
            };

            fetch(`/movies/updateRanking/${movieId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(rankingUpdateDTO)
            })
                .then(response => {
                    if (response.ok) {
                        alert("Ranking updated successfully");
                        // Redirect to detail.html
                        window.location.href = `details.html?movieId=${movieId}`;
                    } else {
                        alert("Error updating ranking");
                    }
                })
                .catch(error => console.error('Error:', error));
        } else {
            alert("Enter a valid ranking between 0 and 10");
        }
    }
}

function showComments(movieId) {

    fetch(`/comments/${movieId}`)
        .then((response) => response.json())
        .then((comments) => {

            const commentsList = document.getElementById("commentsList");
            commentsList.innerHTML = "";

            comments.forEach((comment) => {

                const listItem = document.createElement("li");
                listItem.innerHTML = `<strong>Email:</strong> ${comment.emailAddress}<br><strong>Moment:</strong> ${comment.moment}<br><strong>Comment:</strong> ${comment.comment}`;
                commentsList.appendChild(listItem);
            });

            const commentsSection = document.getElementById("commentsSection");
            commentsSection.style.display = "block";
        })
        .catch((error) => {
            console.error("Error fetching comments:", error);
        });
}






