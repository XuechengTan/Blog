window.addEventListener("load",function () {

    function removeLoginAndSignUp() {
        let nav = document.getElementById("nav");
        while (nav.hasChildNodes()) {
            nav.removeChild(nav.firstChild);
        }
    }

    function addLoggedInNav() {

        let nav = document.getElementById("nav");

        let home = document.createElement("a");
        home.innerHTML = "Home";
        home.setAttribute("href","index.jsp");
        nav.appendChild(home);

        let create = document.createElement("a");
        create.innerHTML = "Add New Article";
        // Just an example of what the name of the file may be
        create.setAttribute("href","CreateArticle.html");
        nav.appendChild(create);

        let profile = document.createElement("a");
        profile.setAttribute("href","Profile.jsp")
        profile.innerHTML = "Profile";
        nav.appendChild(profile);

        let articles = document.createElement("a");
        articles.innerHTML = "My Articles";
        // Just an example of what the name of the file may be
        articles.setAttribute("href","UserArticles.html");
        nav.appendChild(articles)

        let logout = document.createElement("a");
        logout.innerHTML = "Logout";
        // Just an example of what the name of the file may be
        logout.setAttribute("href","Logout.html");
        nav.appendChild(logout);
    }

    // TODO currently these do not run. Need to write a test
    // TODO for when they should run based on checking if the user
    // TODO if logged in.
    //removeLoginAndSignUp();
    //addLoggedInNav();

})