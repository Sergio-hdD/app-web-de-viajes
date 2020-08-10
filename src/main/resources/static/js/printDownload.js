function descargar() {
     var viajeBody = document.getElementById('viajesContainer');
     let nombreArchivo = 'viajes' + '.pdf';
     var opt = {
          margin: 1,
          filename: nombreArchivo,
          enableLinks: true,
          image: { type: 'jpeg', quality: 0.99 },
          html2canvas: {
               scale: 2,
               allowTaint: true
          },
          jsPDF: {
               orientation: 'p',
               unit: 'mm',
               format: [220, 311],
               putOnlyUsedFonts: true
          }
     };
     html2pdf().from(viajeBody).set(opt).save();
}

window.onload = () => {
     // Una vez cargada la pagina remueve el spinner y muestra los viaje
     document.getElementsByTagName("main")[0].classList.remove("invisible");
     let spinnerContainer = document.getElementById("spinnerContainer");
     spinnerContainer.parentElement.removeChild(spinnerContainer);
     // Verifica si el navegador usado no es Chrome
     if (navigator.userAgent.indexOf("Chrome") == -1) {
          document.getElementById("avisoDeCompatibilidad").classList.remove("d-none");
     }
}
