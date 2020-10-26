window.addEventListener("load",function () {
   let selected = document.querySelectorAll("avatar");
   if(selected.values() == "custom"){
       let customDiv = document.getElementById("custom-image");
       let custom = document.createElement("div");
       custom.innerHTML = `
        <input type="file" id="custom-avatar" name="custom-avatar" value="" accept="image/*">`;
       customDiv.appendChild(custom);
   }
});
//This function is used for testing if the two passwords are the same.
function testPassword() {
    let password = document.getElementById("passwordForm");
    let passwordValue = password.password.value;
    let passwordRepeatValue = password.rePassword.value;
    if (!(passwordValue === passwordRepeatValue)) {
        alert("Please check your password, cause two passwords are different");
        return false;
    }
}

//A function to test if the userName is valid.
function testUserName() {

    let userName = document.getElementById("userName");
    // send the username value to servlet and check with existing username and return pass or fail
    fetch(`/usernames?username=${userName.value}`,{
        method:"GET"})
        .then(function (res){
            return res.json();})
        .then(function (text){
            if(text == "fail"){
                alert("Username "+ userName.value +" is already taken!");
                userName.value = "";
            }
        })
        .catch(function (res){console.log(res)})
}

function checkUserName() {

    let userName = document.getElementById("userName");
    // send the username value to servlet and check with existing username and return pass or fail
    fetch(`/usernames?username=${userName.value}`,{
        method:"GET"})
        .then(function (res){
            return res.json();})
        .then(function (text){
            if(text != "fail"){
                alert("Username "+userName.value +" does not exist");
                userName.value = "";
                return false;
            }
        })
        .catch(function (res){console.log(res)})
}