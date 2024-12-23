    function createnewcategory(){
     var newcategory=document.getElementById("newcategoryinput");
    var choosecategory=document.getElementById("selectcategory");
    var selectbtn=document.getElementById("selectcatbtn");
    var newcatbtn=document.getElementById("newcatbtn");

    newcategory.style.display=("block");
    choosecategory.style.display=("none");
     selectbtn.style.display=("block");
    newcatbtn.style.display=("none");
    }

    function selectcategory(){
    var newcategory=document.getElementById("newcategoryinput");
    var choosecategory=document.getElementById("selectcategory");
    var selectbtn=document.getElementById("selectcatbtn");
    var newcatbtn=document.getElementById("newcatbtn");

    newcategory.style.display=("none");
    choosecategory.style.display=("block");
    selectbtn.style.display=("none");
    newcatbtn.style.display=("block");
    }