function showAskBox1(){
    /*let searchDiv = document.getElementById('researchBox').offsetTop;
    alert(searchDiv);
    if(searchDiv > "-129"){*/
        //alert("check - ok - start");
        document.getElementById('researchBox').style.top = "64px";
        document.getElementById('search').style.display = "none";
        document.getElementById('close').style.display = "block";
        document.getElementsByClassName('fadeDisplay')[0].style.opacity = 0;
        document.getElementsByClassName('fadeDisplay')[2].style.opacity = 0;
        //alert("check - ok - end");
    /*}
    else{
        alert("check - no - start");
        document.getElementById('researchBox').style.top = "-128px";
        document.getElementById('search').style.display = "block";
        document.getElementById('close').style.display = "none";
        document.getElementsByClassName('fadeDisplay')[0].style.opacity = 1;
        document.getElementsByClassName('fadeDisplay')[1].style.opacity = 1;
        alert("check - no - end");
    }*/
}

function showAskBox2(){
    document.getElementById('researchBox').style.top = "-128px";
    document.getElementById('search').style.display = "block";
    document.getElementById('close').style.display = "none";
    document.getElementsByClassName('fadeDisplay')[0].style.opacity = 1;
    document.getElementsByClassName('fadeDisplay')[1].style.opacity = 1;
}

function slideBox(id){
    var reponseBox = document.getElementById(id);
    //alert(reponseBox.getElementsByTagName('p')[0].offsetHeight);
    //alert("ok");
    if(reponseBox.getElementsByTagName('p')[0].offsetHeight == "0"){
        reponseBox.style.height = "auto";
        reponseBox.style.padding = "1em";
        setTimeout(function(){ reponseBox.getElementsByTagName('p')[0].style.fontSize = "1em"; }, 300);
    }
    else{
        reponseBox.style.height = "auto";
        reponseBox.style.padding = "0px";
        reponseBox.getElementsByTagName('p')[0].style.fontSize = "0px";
    }
}
function displayAnim(){
    //document.getElementById('preload').style.display = "none";
    document.getElementById('preload').style.opacity = 0;
    setTimeout(function(){ document.getElementById('preload').style.display = "none"; }, 2700);//37
}

function fadeLoad(){
    document.getElementById('preload').style.display = "none";
    //setTimeout(function(){ document.getElementById('preload').style.display = "none"; }, 1000);
}

function displayQuestionBox(id){
    let divState = document.getElementById(id);
    if(divState.style.display === "none" || divState.style.display === ""){
               
        divState.style.display = "block";
        setTimeout(function(){ 
            divState.style.opacity = 1;
            divState.getElementsByTagName('div')[0].style.opacity = 1;
        }, 100);
    }
    else{       
        setTimeout(function(){ 
            divState.style.opacity = 0;
            divState.getElementsByTagName('div')[0].style.opacity = 0;
        }, 100);
        divState.style.display = "none"; 
        
    }
}
function positionnerBox(){
    var widthCon = document.getElementById('connectBox').offsetWidth;
    var navWidth = screen.width;
    var move = (navWidth / 2) - (widthCon / 2) - (navWidth / 28);
    //alert(move);
    document.getElementById('connectBox').style.opacity = 1;
    document.getElementById('connectBox').style.marginLeft =  move + "px";
}
function displayRegister(){
    var widthCon = document.getElementById('connectBox').offsetWidth;
    var navWidth = screen.width;
    var move = (navWidth / 2) - (widthCon / 2) - (navWidth / 28);
    document.getElementById('connectBox').style.opacity = 0;
    document.getElementById('connectBox').style.marginLeft =  0;
    document.getElementById('regsiterBox').style.opacity = 1;
    document.getElementById('regsiterBox').style.marginRight =  move + "px";
}
function displayConnect(){
    var widthCon = document.getElementById('connectBox').offsetWidth;
    var navWidth = screen.width;
    var move = (navWidth / 2) - (widthCon / 2) - (navWidth / 28);
    document.getElementById('connectBox').style.opacity = 1;
    document.getElementById('connectBox').style.marginLeft =   move + "px";
    document.getElementById('regsiterBox').style.opacity = 0;
    document.getElementById('regsiterBox').style.marginRight = 0;
}

document.querySelector('.anonym1').addEventListener("click", disableEmailField);
document.querySelector('.anonym2').addEventListener("click", disableEmailField);

function disableEmailField(){
    //alert(document.getElementById("textForEmail").style.color);
    if(document.getElementById("anonym").checked){
        document.getElementById("textForEmail").style.color = "#ddd";
        document.getElementById("emailTag").disabled = true;
    }
    else{
        document.getElementById("textForEmail").style.color = "black";
        document.getElementById("emailTag").disabled = false;
    }
}

function displayBox()
{
    document.getElementById("errorBox").style.display="none";
}

document.getElementById('searchField').addEventListener("focus", displayResearchAnswer);
document.getElementById('searchField').addEventListener("input", smartSearch);

function displayResearchAnswer(){
    document.getElementById('displayAnsBox').style.display = "block";
    document.getElementById("displayAnsBox").style.zIndex = "1";
    document.getElementById('qfp').style.display = "none";
}

//mon premier moteur de recherche. si jepeux l'appeler coe ça
var finalListResult = "";
function smartSearch(){
    //alert("All Data Set = " + clearData1);
    var noAns = 0;
    var listResult = "";
    //document.getElementById('displayAnsBox').innerHTML=""; 
    let count = 0;
    let userText = "";
    userText = document.getElementById("searchField").value;
    let userSplited = userText.split(" ");
    //alert("var = " + userText + " lenght = " + userText.length + "### " + userSplited.length);
    if(userText.length === 0){
        document.getElementById('displayAnsBox').innerHTML="<p style='text-align: center; color: gray; padding-bottom: 0.3em;'>Oups ! Aucun résultat trouvé</p>";
    }
    if(userText.length >= 2){
        for(let i = 0; i < clearData1.length; i = i + 2){
            for(let j = 0; j < userSplited.length; j++){
                if(clearData1[i].indexOf(userSplited[j]) !== -1){
                    count++;
                }
                //alert(userSplited[j]);
            }
            if(count != 0){
                /*"<span>Lire : <a href='#'>" + clearData1[i] + "</a></span><br>"*/
                let res = "<a href='faq.jsp?categorie=" + clearData1[i+1] + "'><span><span class='black'>Lire : </span><span class='themeSearch'>" + clearData1[i] + "</span> <span class='dataAboutSearch'>dans la <span class='themeSearch'>thématique</span> : " + clearData1[i+1] + "</span> </span></a><br>";
                if(listResult.indexOf(res) === -1){
                    listResult = listResult + res;
                }
                noAns--;
                count = 0;
            }
            finalListResult = finalListResult + listResult;
            document.getElementById('displayAnsBox').innerHTML=listResult;
            if(count === 0){
                noAns++;
                //alert(noAns + " ### " + i/2);
            }
            if(noAns === clearData1.length/2){
                finalListResult = "";
                document.getElementById('displayAnsBox').innerHTML="<p style='text-align: center; color: gray; padding-bottom: 0.3em;'>Oups ! Aucun résultat trouvé</p>";
            }
        }
    }
    //alert(getOffsetPosition('searchField', 'Left') + " , " + getOffsetPosition('searchField', 'Top'));
}

getOffsetPosition = function(inID, inTYPE)
{
    var iVal = 0;
    var oObj = document.getElementById(inID);
    var sType = 'oObj.offset' + inTYPE;
    while (oObj && oObj.tagName != 'BODY') {
        iVal += eval(sType);
        oObj = oObj.offsetParent;
    }
    return iVal;
}

getOffsetDim = function(inID, inTYPE){
    var elm = document.getElementById(inID);
    if(inTYPE === "Width")
        return elm.offsetWidth;
    else if(inTYPE === "Height")
        return elm.offsetHeight;
}

function hideResultBox(event){
    let cursorX = event.clientX;
    let cursorY = event.clientY;
    let searchBoxX = getOffsetPosition('searchField', 'Left');
    let searchBoxY = getOffsetPosition('searchField', 'Top');
    let searchBoxWidth = getOffsetDim('searchField', 'Width');
    let searchBoxHeight = getOffsetDim('searchField', 'Height');
    var isClickedOnSearchField = (cursorX >= searchBoxX && cursorX <= searchBoxX+searchBoxWidth) && (cursorY >= searchBoxY && cursorY <= searchBoxY+searchBoxHeight);
    if(!isClickedOnSearchField){
        //alert("you clicked out side the serach box ");
        document.getElementById("displayAnsBox").style.display = "none";
        document.getElementById('qfp').style.display = "block";
        document.getElementById("displayAnsBox").style.zIndex = "initial";
    }
    
}