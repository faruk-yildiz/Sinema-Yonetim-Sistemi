async function checkAuth(){
    const username=document.getElementById("username").value
    const password=document.getElementById("password").value
    const response = await fetch("http://localhost:8080/api/auth/login",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({
            "username":username,
            "password":password
        })
    })
    if(response.status!=200){
        alert("bir hata oluştu")
    }else{
        localStorage.setItem("username",username)
        localStorage.setItem("password",password)
        window.location.replace("edit.html")
    }
}

