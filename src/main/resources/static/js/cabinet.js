// const managePanelButton = document.querySelector('.menu-item__button--panel');
// console.log(managePanelButton);

// managePanelButton.state = false;

const $asideMenu = document.querySelector('.aside__menu');
const asideMenu = [...document.querySelectorAll('.menu-item__button')];
// console.log(asideMenu);


// Для перехода из карточки ШАГ1 в окно баланса
const balanceChargeInit = () => {
  const managePanelButton = document.querySelector('.menu-item__button--panel');
  const targetMenuButton = document.querySelector('.menu-item__button--recharge');
  const balanceCharge = document.querySelector('.balance');
    // console.log(balanceCharge);

    balanceCharge.addEventListener('click', (e) => {
      walletWindow.classList.remove('hidden');
      historyBlock.classList.remove('hidden');

      chargeButton.classList.remove('hidden');
      chargeButton.classList.add('buttons-section__button--active');
      chargeButton.childNodes[1].classList.add('buttons-section__icon--active');
      subscribeButton.classList.remove('hidden');
      withdrawButton.classList.remove('hidden');
      
      managePanelButton.classList.remove('menu-item__button--active');
      targetMenuButton.classList.add('menu-item__button--active');

      stepsWindow.classList.add('hidden');
      traderWindow.classList.add('hidden');
      myTraderCard.classList.add('hidden');
      withdrawWindow.classList.add('hidden');
    });
}

// steps 
const stepsWindow = document.querySelector('.container_nav-panel');
const managePanelButton = document.querySelector('.menu-item__button--panel');

managePanelButton.classList.add('menu-item__button--active');
balanceChargeInit();

// trader
const traderWindow = document.querySelector('.main_content_wraper');
const myTraderCard = document.querySelector('.main_content_wraper--trader');

// withdraw 
const withdrawWindow = document.querySelector('.main_content_wraper--withdraw');

// recharge

const walletWindow = document.querySelector('.wallet-section');
const historyBlock = document.querySelector('.history-block');

// subscription 

const subscribtionForTrader = document.querySelector('.main_content_wraper--subscribe');

// buttons (top panel)

const buttonsCollection = [...document.querySelectorAll('.buttons-section__button')];

// changing the state of top panel buttons
const resetButtonsTopPanel = (exeption) => {
  buttonsCollection.forEach((item) => {
    if (item !== exeption) {
      item.classList.remove('buttons-section__button--active');
      item.childNodes[1].classList.remove('buttons-section__icon--active');
      item.isActive = false;
    }
  });
}

buttonsCollection.forEach((item) => {
  // console.log(item);
  item.isActive = false;
  item.addEventListener('click', (e) => {
    // console.log('click');
    if (!item.isActive || e.target === item.childNodes[1]) {
      // console.log(item.isActive);
      item.classList.add('buttons-section__button--active');
      // console.log(item.childNodes[1]);
      item.childNodes[1].classList.add('buttons-section__icon--active');
      item.isActive = true;
      // console.log(item.isActive);
      resetButtonsTopPanel(item);
    } 
  });
});


const traderButton = document.querySelector('.buttons-section__button--trader');
const topButton = document.querySelector('.buttons-section__button--top');
const chargeButton = document.querySelector('.buttons-section__button--charge');
const subscribeButton = document.querySelector('.buttons-section__button--subscribe');
const withdrawButton = document.querySelector('.buttons-section__button--withdraw');

const testFuntion = () => {
  setTimeout(() => {
    walletCardRate.dataset.count =  0.1034954;
  }, 3000);
}

// traders gallery

const tradersGallery = document.querySelector('.card-section');

topButton.addEventListener('click', (e) => {
  tradersGallery.classList.remove('hidden');

  traderWindow.classList.add('hidden');
  myTraderCard.classList.add('hidden');
});

traderButton.addEventListener('click', (e) => {
  tradersGallery.classList.add('hidden');

  traderWindow.classList.remove('hidden');
  myTraderCard.classList.remove('hidden');
});

subscribeButton.addEventListener('click', (e) => {
  subscribtionForTrader.classList.remove('hidden');

  traderWindow.classList.add('hidden');
  myTraderCard.classList.add('hidden');
  walletWindow.classList.add('hidden');
  historyBlock.classList.add('hidden');
  withdrawWindow.classList.add('hidden');
});

withdrawButton.addEventListener('click', (e) => {
  withdrawWindow.classList.remove('hidden');

  subscribtionForTrader.classList.add('hidden');
});

chargeButton.addEventListener('click', (e) => {
  walletWindow.classList.remove('hidden');
  historyBlock.classList.remove('hidden');

  traderWindow.classList.add('hidden');
  myTraderCard.classList.add('hidden');
  withdrawWindow.classList.add('hidden');
  subscribtionForTrader.classList.add('hidden');
});

asideMenu.forEach((item) => {
  item.isActive = false;
});

const buttonsStateReset = (exeption) => {

  asideMenu.forEach(item => {
    // console.dir(item);
    item.isActive = false;
    exeption.classList.remove('menu-item__button--active');

    if (item === exeption) {
      item.isActive = true;
      // console.log('exeption');
      // console.log(item);
    //   exeption.classList.add('menu-item__button--active');
    } else {
      item.classList.remove('menu-item__button--active');
    }
  });
}

$asideMenu.addEventListener('click', (e) => {
  if (e.target.classList.contains('menu-item__button')) {
    if (e.target.classList.contains('menu-item__button--panel')) {
      e.target.isActive = true;
   
      if (e.target.isActive) {
        buttonsStateReset(e.target);
        e.target.classList.add('menu-item__button--active');
      } 

      chargeButton.classList.add('hidden');
      subscribeButton.classList.add('hidden');
      withdrawButton.classList.add('hidden');

      traderButton.classList.add('hidden');
      topButton.classList.add('hidden');




    //   steps activation
      stepsWindow.classList.remove('hidden');

      traderWindow.classList.add('hidden');
      myTraderCard.classList.add('hidden');
      walletWindow.classList.add('hidden');
      historyBlock.classList.add('hidden');
  
      balanceChargeInit();
    }

    if (e.target.classList.contains('menu-item__button--trader')) {
      e.target.isActive = true;

      if (e.target.isActive) {
        buttonsStateReset(e.target);
        e.target.classList.add('menu-item__button--active');
      } 

      traderButton.classList.remove('hidden');
      traderButton.classList.add('buttons-section__button--active');
      traderButton.childNodes[1].classList.add('buttons-section__icon--active');
      topButton.classList.remove('hidden');

      chargeButton.classList.add('hidden');
      subscribeButton.classList.remove('hidden');
      withdrawButton.classList.add('hidden');

    
      traderWindow.classList.remove('hidden');
      myTraderCard.classList.remove('hidden');

      stepsWindow.classList.add('hidden');
      withdrawWindow.classList.add('hidden'); 
      walletWindow.classList.add('hidden');
      historyBlock.classList.add('hidden');
    }

    if (e.target.classList.contains('menu-item__button--recharge')) {
        e.target.isActive = true;
  
      if (e.target.isActive) {
        buttonsStateReset(e.target);
        e.target.classList.add('menu-item__button--active');
        
      }


      chargeButton.classList.remove('hidden');
      chargeButton.classList.add('buttons-section__button--active');
      chargeButton.childNodes[1].classList.add('buttons-section__icon--active');
      subscribeButton.classList.add('hidden');
      withdrawButton.classList.remove('hidden');

      traderButton.classList.add('hidden');
      topButton.classList.add('hidden');

      
      
      walletWindow.classList.remove('hidden');
      historyBlock.classList.remove('hidden');
      testFuntion();

      stepsWindow.classList.add('hidden');
      traderWindow.classList.add('hidden');
      myTraderCard.classList.add('hidden');
      withdrawWindow.classList.add('hidden');
    }

    if (e.target.classList.contains('menu-item__button--settings')) {
        e.target.isActive = true;
  
      if (e.target.isActive) {
        buttonsStateReset(e.target);
        e.target.classList.add('menu-item__button--active');
      } 

      traderButton.classList.add('hidden');
      topButton.classList.add('hidden');
      chargeButton.classList.add('hidden');
      subscribeButton.classList.add('hidden');
      withdrawButton.classList.add('hidden');

      stepsWindow.classList.add('hidden');
      traderWindow.classList.add('hidden');
      myTraderCard.classList.add('hidden');
      withdrawWindow.classList.add('hidden'); 
      walletWindow.classList.add('hidden');
      historyBlock.classList.add('hidden');
    }

    if (e.target.classList.contains('menu-item__button--exit')) {
        e.target.isActive = true;
  
      if (e.target.isActive) {
        buttonsStateReset(e.target);
        e.target.classList.add('menu-item__button--active');
      } 
    }
  }
});


// ПОПОЛНЕНИЕ

const walletCardRate = document.querySelector('.wallet-card__rate');
console.log(walletCardRate);

const configForObserver = {
  attributes: true, 
  attributeFilter: ['data-count'],
  childList: true, 
  subtree: true,
  textContent: true
}

// пробрасывает значение из аттрибута в текст контент
const transferValueToTextContent = (elem, value) => {
  elem.textContent = value;
}

// коллбек для наблюдателя изменения значения аттрибута
// аттрибут меняется и если значение в поле положительно 
// через секунду выбрасывает на страницу с шагами
const observerCallBack = (mutationsList) => {
    for(var mutation of mutationsList) {
        
        if (mutation.type == 'attributes') {
            transferValueToTextContent(mutation.target, mutation.target.dataset.count);
           
            if (mutation.target.dataset.count > 0) {
                setTimeout(() => {
                  walletWindow.classList.add('hidden');
                  historyBlock.classList.add('hidden');
              
                  stepsWindow.classList.remove('hidden');
                  
                //   Изменение состояния карточек с шагами
                    const cardButtonStep2 = document.querySelector('.card_item__button.trader');
                    const cardButtonStep1 = document.querySelector('.card_item__button.balance');
                    console.log(cardButtonStep2);
                    console.log(cardButtonStep1);

                    cardButtonStep2.classList.remove('card_item__button--disabled');
                    cardButtonStep2.removeAttribute('disabled', '');
                    cardButtonStep1.classList.add('card_item__button--disabled');
                    cardButtonStep1.setAttribute('disabled', '');

                    getCardMagic();
                }, 1000);
            }
        }
    }
}

const observer = new MutationObserver(observerCallBack);
observer.observe(walletCardRate, configForObserver);