function loadProfileData(){

    const token = localStorage.getItem('jwt');
    if (!token) {
        Toastify({
            text: "You aren't logged in",
            duration: 3000,
            close: true,
            gravity: "top",
            position: "right",
            backgroundColor: "#dc2626",
            stopOnFocus: true,
        }).showToast();
        setTimeout(() => {
            window.location.href = '/login';
        }, 1000);
        return;
    }
    const name = document.getElementById("first-name").innerHTML =
        `${parseJwt(token).userName}`;
}