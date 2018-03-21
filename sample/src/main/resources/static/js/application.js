$(function() {
  $('.confirm').click(function() {
      if (confirm('You sure?')) {
        $(this).parent().submit();
      } else {
        return false;
      }
    });
});
