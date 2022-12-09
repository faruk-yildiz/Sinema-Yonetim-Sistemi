var url="http://localhost:8080/api/"
var movieSelectBox=document.getElementById("film")
var saloonSelectBox=document.getElementById("salon")
var sessionSelectBox=document.getElementById("seans")

async function getMovies(){
    const response = await fetch(url+"movies");
    var data = await response.json();

    data.forEach(element => {
        const optionElement=document.createElement("option")
        optionElement.setAttribute("id",element.id)
        optionElement.innerText=element.tittle
        movieSelectBox.appendChild(optionElement)
    })
}

//Film değişince salonları getir
$("#film").click(function() {
    var id = $(this).children(":selected").attr("id");
    getSaloons(id)
  });
//Salonları değişince seansları getir
$("#salon").click(function() {
    
    var id = $(this).children(":selected").attr("id");
    getSessionAndExpireDate(id.split("-")[1])
    
  });

async function getSessionAndExpireDate(id){
    $("#seans").empty()
    $("#tarih").empty()
    const dateInput=document.getElementById("tarih")
    const response = await fetch(url+"saloons/"+id);
    var data = await response.json();
    dateInput.setAttribute("min",new Date().toLocaleDateString('sv'))
    dateInput.setAttribute("max",data.expire_date)
    var sessionArray=data.sessions.split(";")
    sessionArray.forEach(element=>{
        if(element!=""){
        const option=document.createElement("option")
        option.innerText=element
        sessionSelectBox.appendChild(option)
        }
    })
}

async function getSaloons(id){
    $("#salon").empty()
    const response = await fetch(url+"saloons/findbymovie/"+id);
    var data = await response.json();

    data.forEach(element => {
        const optionElement=document.createElement("option")
        optionElement.setAttribute("id","salon-"+element.id)
        optionElement.innerText=element.name
        saloonSelectBox.appendChild(optionElement)
    })
}
getMovies()

async function buyTicket(e){
    const email=document.getElementById("email")
    const phone=document.getElementById("telefon")
    const movie_id=$("#film").children(":selected").attr("id")
    const saloon_id=$("#salon").children(":selected").attr("id").split("-")[1]
    const date=document.getElementById("tarih")
    const session=document.getElementById("seans")
    const modalEmail=document.getElementById("modalEmail")
    const modalPhone=document.getElementById("modalTelefon")
    const modalFilm=document.getElementById("modalFilm")
    const modalSalon=document.getElementById("modalSalon")
    const modalTarih=document.getElementById("modalTarih")
    const modalSeans=document.getElementById("modalSeans")
    const response=await fetch(url+"tickets",{
        method:'POST',
        headers:{
            'Content-Type':'application/json'
        },
        body:JSON.stringify({
            "date":date.value,
            "email":email.value,
            "movie_id":movie_id,
            "phone":phone.value,
            "saloon_id":saloon_id,
            "session":session.value
        })
    })

    if(response.status==200){
        alert("İşlem Başarılı")
        modalEmail.value=email.value
        modalPhone.value=phone.value
        modalFilm.value=$("#film").children(":selected").text()
        modalSalon.value=$("#salon").children(":selected").text()
        modalTarih.value=date.value
        modalSeans.value=session.value
        $('#ticketModal').modal("toggle");
        $('#ticketModal').modal('show')
    }
    else{
        alert("Bir hata oluştu")
    }
}


function refresh(){
    window.location.reload(true)
}

