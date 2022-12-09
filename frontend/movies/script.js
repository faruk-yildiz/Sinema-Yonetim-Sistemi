const url="http://localhost:8080/api/movies/"
const container=document.getElementById("flex-container")

async function getMovies(url){
    const response = await fetch(url);
    var data = await response.json();

    data.forEach(element => {
        const divElement=document.createElement("div")
        divElement.setAttribute("class","card m-5")
        divElement.setAttribute("style","min-width:13rem; max-width:15rem; height:36rem")
        const movieImg=document.createElement("img")
        movieImg.setAttribute("class","card-img-top")
        movieImg.src="data:image/png;base64," + element.imageModel.picByte;
        divElement.appendChild(movieImg)
        const cardBody=document.createElement("div")
        cardBody.setAttribute("class","card-body text-center overflow-auto")
        const tittle=document.createElement("h5")
        tittle.setAttribute("class","card-title")
        tittle.innerText=element.tittle
        cardBody.appendChild(tittle)
        const cardText=document.createElement("p")
        cardText.innerText=element.description
        cardBody.appendChild(cardText)
        const button=document.createElement("a")
        button.setAttribute("class","btn mx-5 my-3 btn-primary")
        button.setAttribute("data-toggle","modal")
        button.setAttribute("id",element.id)
        button.setAttribute("onclick","sendToModal(this.id)")
        button.setAttribute("data-target","#exampleModal")
        button.innerText="Detaylı Bilgi..."
        divElement.appendChild(cardBody)
        divElement.appendChild(button)
        container.appendChild(divElement)
    
    })
}//data-toggle="modal" data-target="#exampleModal"
getMovies(url)

async function sendToModal(id){
    const response = await fetch(url+id);
    var data = await response.json();
    const title=document.getElementById("modal-title")
    title.innerText=data.tittle
    const director=document.getElementById("director")
    director.innerText=data.director
    const year=document.getElementById("year")
    year.innerText=data.year
    const description=document.getElementById("description")
    description.innerText=data.description
    const imgField=document.getElementById("imgField")
    imgField.src="data:image/png;base64," + data.imageModel.picByte;
}