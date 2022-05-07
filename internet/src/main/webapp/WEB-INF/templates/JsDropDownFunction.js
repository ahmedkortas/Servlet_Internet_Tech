function myFunctionDropDown() {
  console.log("dropdown");
  let x = document.getElementById("katalog");
  console.log(x.style.display);
  if (x.style.display == "flex") {
    x.style.display = "none";
  } else {
    x.style.display = "flex";
  }
}

function myFunctionSwal() {
  Swal.fire({
    position: "center",
    icon: "success",
    title: "Your work has been saved",
    showConfirmButton: false,
    timer: 3000,
  });
  setTimeout(() => {
    window.location.href = "index.html";
  }, 3000);
}
