;(function() { 
let $rangeElem = $('.range_item');
let $container = $('.overflow_container');
let newElem = $('<div class="range_progress"></div>')
$container.prepend($('<div class="range_progress"></div>'));
func(true);	
$rangeElem.mousemove(func);
$rangeElem.change(func);
$rangeElem.click(func);
function func(render){	
  	
let $width = $(this).closest('.range_item').val();
  	

if ($(this).closest('.range_item').attr('max')>20) {

	$(this).closest('.overflow_container').children('.range_progress').css(
		{width: $width*.95+'%'});
	}
	else{

		let w = {
					width:( $width - 4) * 5.89 +'%'
				};
		if (!($(this).closest('.overflow_container').children('.procent').hasClass('font'))) {
			$('.procent').removeClass('font');
			$('.procent._'+ $width).addClass('font');
		}

		$(this).closest('.overflow_container').children('.range_progress').css( w );

	}
}

$('.range_item').toArray().forEach( (item) => {
	
	func.call(item);
});

// let toggleFlag = false;
$('.header__menu-button').click(function(e){
    // toggleFlag = true;
	if ($('.aside').hasClass('toggle_aside')) {
	 $('.aside').removeClass('toggle_aside');
	 $('.cabinet-wrapper').removeClass('toggle_cabinet');
	 $('.menu-item__button-icon').removeClass('menu-item__button-icon--toggle');
	}
	else{
	 
	$('.aside').addClass('toggle_aside');
	// if ()
	$('.menu-item__button-icon').addClass('menu-item__button-icon--toggle');
	$('.cabinet-wrapper').addClass('toggle_cabinet');
	}
   
   });

})();