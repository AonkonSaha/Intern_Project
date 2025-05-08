function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    })
        .then(response => response.json())
        .then(data => {
            if (data.token) {
                localStorage.setItem('jwt', data.token);
                alert("Login successful!");
                window.location.href = '/home.html';


            } else {
                alert("Invalid login credentials");
            }
        })
        .catch(error => {
            console.error("Error during login:", error);
            alert("Error during login");
        });
}

function register() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const role = document.getElementById("role").value;

    fetch('/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password, role })
    })
        .then(response => response.text())
        .then(data => {
            alert(data);
            window.location.href = '/login';
        })
        .catch(error => {
            console.error("Error during registration:", error);
            alert("Error during registration");
        });
}

function checkAuth() {
    const token = localStorage.getItem('jwt');

    if (!token) {
        alert("You are not logged in!");
        window.location.href = '/login'; // Redirect to login if no token
    } else {
        fetch('/api', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(response => response.json())
            .then(data => {
                console.log(data); // Do something with the protected data
            })
            .catch(error => {
                console.error("Error fetching protected data:", error);
            });
    }
}

function logout() {
    const token = localStorage.getItem('jwt');

    if (!token) {
        window.location.href = '/login';
        return;
    }

    fetch('/logout', {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Logout failed");
            }
            return response.text();
        })
        .then(data => {
            console.log("Logged out:", data);
        })
        .catch(error => {
            console.error("Error during logout:", error);
        })
        .finally(() => {
            localStorage.removeItem('jwt');
            window.location.href = '/login';
        });
}

function loadHomeData() {
    const token = localStorage.getItem('jwt');
    if (!token) {
        alert("You are not logged in");
        window.location.href = '/login';
        return;
    }

    fetch('/home', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response => {
            if (response.status === 403 || response.status === 401) {
                throw new Error("Unauthorized");
            }
            return response.text();
        })
        .then(html => {
            document.getElementById('home-content').innerHTML = html;
        })
        .catch(error => {
            console.error("Error loading home content:", error);
            alert("Failed to load home content. Please log in again.");
            window.location.href = '/login';
        });
}
