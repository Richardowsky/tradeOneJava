
const hiddenCard = document.querySelector('.card_item.card_1');
const showingCard = document.querySelector('.acc_rep');

const getCardMagic = () =>{
		setTimeout(() => {
			hiddenCard.classList.add('rotateHidden');
		setTimeout(() =>{hiddenCard.classList.add('hidden')}, 500);

		setTimeout(()=>{

			setTimeout(()=>{showingCard.classList.remove('hidden')},100)
			setTimeout(()=>{
				showingCard.classList.add('rotation180')
			},400)
			
		}, 400);
	}, 500);


}
// hiddenCard.children[1].addEventListener('click', getCardMagic);
