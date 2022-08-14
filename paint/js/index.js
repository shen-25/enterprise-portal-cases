 var flag = false;
 $('#meun-title').click(function() {
     if (flag) {
         $('#meun1').hide();
         flag = false;
     } else {
         $('#meun1').show();

         flag = true;
     }
 });

 $('#meun').mouseleave(function() {
     $('#meun1').hide();
     flag = false;
 })