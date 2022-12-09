const url="http://localhost:8080/api/"
var tableBody=document.getElementById("table-body")
var saloonTableBody=document.getElementById("saloon-table-body")
const expireDateField=document.getElementById("salon-tarih")
expireDateField.setAttribute("min",new Date().toLocaleDateString('sv'))
//check authentication
function checkToken(){
    let storage=localStorage
    if(storage.getItem("username")==null && storage.getItem("password")==null)
    {
        window.location.replace("login.html")
    }
}
checkToken()

window.addEventListener('unhandledrejection', function (e) {
    alert("Error occurred: " + e.reason.message);
  })
//filmleri yükle
async function getMovies(url){
    const saloonChoose=document.getElementById("salon-film")
    const response = await fetch(url+"movies")
    var data = await response.json();
    data.forEach(element => {
        //salon ekle film seçeneklerinin doldurulması
        const option=document.createElement("option")
        option.innerText=element.id+":"+element.tittle
        option.setAttribute("id",element.id)
        saloonChoose.appendChild(option)
        const row=document.createElement("tr")
        const col1=document.createElement("td")
        const col2=document.createElement("td")
        const col3=document.createElement("td")
        const col4=document.createElement("td")
        const col5=document.createElement("td")
        col5.setAttribute("style","width:150px")
        const editButton=document.createElement("a")
        editButton.setAttribute("class","btn btn-primary")
        editButton.innerText="Düzenle"
        editButton.setAttribute("onclick","editMovie(this)")
        const deleteButton=document.createElement("a")
        deleteButton.setAttribute("class","btn btn-danger")
        deleteButton.setAttribute("onclick","deleteMovie(this)")
        deleteButton.innerText="Sil"
        col1.innerText=element.id
        col2.innerText=element.tittle
        col3.innerText=element.director
        col4.innerText=element.year
        col5.appendChild(editButton)
        col5.appendChild(deleteButton)
        row.appendChild(col1)
        row.appendChild(col2)
        row.appendChild(col3)
        row.appendChild(col4)
        row.appendChild(col5)
        tableBody.appendChild(row)
    });
}
//salonları yükle
async function getSaloons(url){
    
    const response = await fetch(url+"/saloons")
    var data = await response.json();
    data.forEach(element => {
        const row=document.createElement("tr")
        const col1=document.createElement("td")
        const col2=document.createElement("td")
        const col3=document.createElement("td")
        const col4=document.createElement("td")
        const col5=document.createElement("td")
        const editButton=document.createElement("a")
        editButton.setAttribute("class","btn btn-primary")
        editButton.innerText="Düzenle"
        editButton.setAttribute("onclick","editSaloon(this)")
        const deleteButton=document.createElement("a")
        deleteButton.setAttribute("class","btn btn-danger")
        deleteButton.setAttribute("onclick","deleteSaloon(this)")
        deleteButton.innerText="Sil"
        col1.innerText=element.id
        col2.innerText=element.name
        col3.innerText=element.movie_id
        col4.innerText=element.expire_date
        col5.appendChild(editButton)
        col5.appendChild(deleteButton)
        row.appendChild(col1)
        row.appendChild(col2)
        row.appendChild(col3)
        row.appendChild(col4)
        row.appendChild(col5)
        saloonTableBody.appendChild(row)

    })
} 
//kayıt silme
async function deleteMovie(e) {
    var row=e.closest("tr")
    var id=(row.firstChild.innerText)
    const response = await fetch(url+"movies/"+id, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json'
        }
    });
    if(response.status==200)
    {
        alert("Kayıt silindi")
        window.location.reload(true)
    }
    else{
        alert("Bir hata oluştu")
    }
}
//tablodaki datayı film düzenle paneline aktarma
async function editMovie(e) {
    var row=e.closest("tr")
    var id=(row.firstChild.innerText)
    const response = await fetch(url+"movies/"+id);
    var data=await response.json()
    const myFile = new File(["data:image/png;base64," + data.imageModel.picByte], data.imageModel.name, {
        type: 'image/png'
    })

    
    const dataTransfer = new DataTransfer();
    dataTransfer.items.add(myFile);

    const imgField=document.getElementById("customFile")
    imgField.files = dataTransfer.files;
    const idField=document.getElementById("id")
    idField.value=data.id
    const tittle=document.getElementById("title")
    tittle.value=data.tittle
    const year=document.getElementById("yil")
    year.value=data.year
    const duration=document.getElementById("sure")
    duration.value=data.duration
    const director=document.getElementById("yonetmen")
    director.value=data.director
    const description=document.getElementById("konu")
    description.value=data.description
    $('#pills-tab button[id="pills-film-edit-tab"]').tab('show')
}
//film bilgilerini güncelle onClick
async function updateOrAddMovie(){
    const idField=document.getElementById("id")
    const imgField=document.getElementById("customFile")
    const tittle=document.getElementById("title")
    const year=document.getElementById("yil")
    const duration=document.getElementById("sure")
    const director=document.getElementById("yonetmen")
    const description=document.getElementById("konu")
    if(idField.value===""){
        const formData = new FormData()
        formData.append('movie',new Blob([JSON.stringify({
            "tittle":tittle.value,
            "year":year.value,
            "duration":parseInt(duration.value),
            "director":director.value,
            "description":description.value
        })],{"type":"application/json"}))
        formData.append('image',imgField.files[0])
        const res = await fetch(url+"movies", {
          body:formData,
          method: 'POST',
        })
        if(res.status==200){
            alert("Kayıt başarılı")
            window.location.reload(true)
        }
        else{
            alert("Bir hata oluştu")
        }
    }
    else{
        const id=idField.value
        const formData = new FormData()
        formData.append('movie',new Blob([JSON.stringify({
            "tittle":tittle.value,
            "year":year.value,
            "duration":parseInt(duration.value),
            "director":director.value,
            "description":description.value
        })],{"type":"application/json"}))
        formData.append('image',imgField.files[0])
        const res = await fetch(url+"movies/"+id, {
          body:formData,
          method: 'PUT',
        })
        if(res.status==200){
            alert("Güncelleme başarılı")
            window.location.reload(true)
        }          
        else{
            alert("Bir hata oluştu")
        }
    }
}
//salon ekle veya güncelle 
async function updateOrAddSaloon(){
    const idField=document.getElementById("salon-id")
    const name=document.getElementById("salon-adi")
    const capacity=document.getElementById("salon-kapasite")
    const movie=document.getElementById("salon-film")
    const movieID=movie.value.split(":")[0]
    const sessions=document.getElementById("salon-seans-goster")
    const expireDate=document.getElementById("salon-tarih")
    if(idField.value===""){
        const res = await fetch(url+'saloons',{
          method:'POST',
          body:JSON.stringify({
            "capacity":parseInt(capacity.value),
            "expire_date":expireDate.value,
            "name":name.value,
            "movie_id":movieID,
            "sessions":sessions.value
        }),
            headers:{
                'Content-Type':'application/json'
            }
        })
        if(res.status==200){
            alert("Kayıt başarılı")
            window.location.reload(true)
        }
        else{
            alert("Bir hata oluştu")
        }
    }
    else{
        const id=document.getElementById("salon-id").value
        const res = await fetch(url+"saloons/"+id, {
            body:JSON.stringify({
                "capacity":parseInt(capacity.value),
                "expire_date":expireDate.value,
                "name":name.value,
                "movie_id":movieID,
                "sessions":sessions.value
            }),
                method:'PUT',
                headers:{
                 'Content-Type':'application/json'
                 }
            })
        if(res.status==200){
            alert("Güncelleme başarılı")
            window.location.reload(true)
        }          
        else{
            alert("Bir hata oluştu")
        }
    }
}
//salon sil
async function deleteSaloon(e){
    var row=e.closest("tr")
    var id=(row.firstChild.innerText)
    const response = await fetch(url+"saloons/"+id, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json'
        }
    });
    if(response.status==200)
    {
        alert("Kayıt silindi")
        window.location.reload(true)
    }
    else{
        alert("Bir hata oluştu")
    }
}
//tablodaki detayı salon düzenle paneline aktarma
async function editSaloon(e){
    var row=e.closest("tr")
    var id=(row.firstChild.innerText)
    const response = await fetch(url+"saloons/"+id);
    var data=await response.json()
    const idField=document.getElementById("salon-id")
    idField.value=data.id
    const name=document.getElementById("salon-adi")
    name.value=data.name
    const capacity=document.getElementById("salon-kapasite")
    capacity.value=data.capacity
    const movie=document.getElementById("salon-film")
    movie.value=data.movie_id
    const sessions=document.getElementById("salon-seans-goster")
    sessions.value=data.sessions
    const expireDate=document.getElementById("salon-tarih")
    expireDate.value=data.expire_date

    $('#pills-tab button[id="pills-salon-edit-tab"]').tab('show')
}

function addSession(){
    const timePickerValue=document.getElementById("salon-seans").value
    const showSession=document.getElementById("salon-seans-goster")
    showSession.value=showSession.value+timePickerValue+";"
}

function clearStorage(){
    localStorage.removeItem("username")
    localStorage.removeItem("password")
    window.location.reload(true)
}

getMovies(url)
getSaloons(url)
/*async function getMovie(id){
    const response=await fetch(url+"movies/"+id)
    const data=response.json()
    return data
}*/

//const response = await fetch(url+"movies/"+movie.value.split(":")[0])
//var data = await response.json();