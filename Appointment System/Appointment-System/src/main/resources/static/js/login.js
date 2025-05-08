// document.getElementById('loginForm').addEventListener('submit', async function (e) {
//     e.preventDefault();
//     const username = e.target.username.value;
//     const password = e.target.password.value;
//
//     const response = await fetch('/login', {
//         method: 'POST',
//         headers: { 'Content-Type': 'application/json' },
//         body: JSON.stringify({ username, password })
//     });
//
//     const data = await response.json();
//     if (response.ok) {
//         localStorage.setItem('jwt', data.token);
//         window.location.href = "/home";
//     } else {
//         document.getElementById('error').textContent = data.message || 'Login failed';
//     }
// });
