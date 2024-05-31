document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('login-form');
    const messageElement = document.getElementById('message-login');

    loginForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        fetch('http://localhost:8080/authenticate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Invalid username or password');
                }
                return response.json();
            })
            .then(data => {
                const token = data.token;
                localStorage.setItem('token', token);
                localStorage.setItem('username', username);
                window.location.href = 'home.html';
            })
            .catch(error => {
                console.error('Login failed:', error);
                messageElement.textContent = "Usuário ou senha inválidos!";
            });

    });
});
