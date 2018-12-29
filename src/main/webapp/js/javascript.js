function onlyNumbers(evt)
{
    var e = event || evt; // for trans-browser compatibility
    var charCode = e.which || e.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

var check = function() {
    if (document.getElementById('pw1').value ==
        document.getElementById('pw2').value) {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = 'Hesla se shodují';
    } else {
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = 'Hesla se neshodují';
    }
}

function skryt()
{
    var x = document.getElementById("vlozknihu");
    var y = document.getElementById("spravarezer");
    var z = document.getElementById("spravavypujcek");
    var zz = document.getElementById("spravaupominek");
    var xy = document.getElementById("spravauzivatele");
    var xyz = document.getElementById("spravarecenze");


    if (x.style.display === "none") {
        x.style.display = "block";
    }else{
        x.style.display = "none"
    }
    y.style.display="none";
    z.style.display="none";
    zz.style.display="none";
    xy.style.display="none";
    xyz.style.display="none";
}

function skryt1()
{
    var x = document.getElementById("vlozknihu");
    var y = document.getElementById("spravarezer");
    var z = document.getElementById("spravavypujcek");
    var zz = document.getElementById("spravaupominek");
    var xy = document.getElementById("spravauzivatele");
    var xyz = document.getElementById("spravarecenze");

    if (y.style.display === "none") {
        y.style.display = "block";
    }else{
        y.style.display = "none"
    }
    x.style.display="none";
    z.style.display="none";
    zz.style.display="none";
    xy.style.display="none";
    xyz.style.display="none";

}

function skryt2()
{
    var x = document.getElementById("vlozknihu");
    var y = document.getElementById("spravarezer");
    var z = document.getElementById("spravavypujcek");
    var zz = document.getElementById("spravaupominek");
    var xy = document.getElementById("spravauzivatele");
    var xyz = document.getElementById("spravarecenze");

    if (z.style.display === "none") {
        z.style.display = "block";
    }else{
        z.style.display = "none"
    }
    y.style.display="none";
    x.style.display="none";
    zz.style.display="none";
    xy.style.display="none";
    xyz.style.display="none";

}

function skryt3()
{
    var x = document.getElementById("vlozknihu");
    var y = document.getElementById("spravarezer");
    var z = document.getElementById("spravavypujcek");
    var zz = document.getElementById("spravaupominek");
    var xy = document.getElementById("spravauzivatele");
    var xyz = document.getElementById("spravarecenze");

    if (zz.style.display === "none") {
        zz.style.display = "block";
    }else{
        zz.style.display = "none"
    }
    y.style.display="none";
    z.style.display="none";
    x.style.display="none";
    xy.style.display="none";
    xyz.style.display="none";
}

function skryt4()
{
    var x = document.getElementById("vlozknihu");
    var y = document.getElementById("spravarezer");
    var z = document.getElementById("spravavypujcek");
    var zz = document.getElementById("spravaupominek");
    var xy = document.getElementById("spravauzivatele");
    var xyz = document.getElementById("spravarecenze");

    if (xy.style.display === "none") {
        xy.style.display = "block";
    }else{
        xy.style.display = "none"
    }
    y.style.display="none";
    z.style.display="none";
    x.style.display="none";
    zz.style.display="none";
    xyz.style.display="none";
}

function skryt5()
{
    var x = document.getElementById("vlozknihu");
    var y = document.getElementById("spravarezer");
    var z = document.getElementById("spravavypujcek");
    var zz = document.getElementById("spravaupominek");
    var xy = document.getElementById("spravauzivatele");
    var xyz = document.getElementById("spravarecenze");

    if (xyz.style.display === "none") {
        xyz.style.display = "block";
    }else{
        xyz.style.display = "none"
    }
    y.style.display="none";
    z.style.display="none";
    x.style.display="none";
    zz.style.display="none";
    xy.style.display="none";
}