function validateForm()
{
    var a=document.forms["Form"]["answer_a"].value;
    var b=document.forms["Form"]["answer_b"].value;
    var c=document.forms["Form"]["password"].value;
    if (a==null || a=="",b==null || b=="",c==null || c=="")
    {
        alert("Please Fill All Required Field");
        return false;
    }
}