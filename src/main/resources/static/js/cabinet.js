

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

const directory = '';
const urlImg = [directory+'1 (1).png',directory+'1 (2).png',directory+'1 (3).png',directory+'1 (4).png',directory+'1 (5).png',directory+'1 (6).png',directory+'1 (7).png',directory+'1 (8).png',
  directory+'1 (9).png',directory+'1 (10).png',directory+'1 (11).png',directory+'1 (12).png',directory+'1 (13).png',directory+'1 (14).png',directory+'1 (15).png',directory+'1 (16).png'
  ,directory+'1 (17).png',directory+'1 (18).png',directory+'1 (19).png',directory+'1 (20).png',directory+'1 (21).png']




function renderingTraders(obj, item){
  //создаем элементы

  const traderName = obj.traders[item].firstname+' ' + obj.traders[item].lastname;
  const procent = obj.traders[item].profit + '%';
  const followers = obj.traders[item].investors;
// const subscribers;
  let template = `<div class="card-section__card card">
      <div class="card__avatar" style = "background-image: url('${urlImg[item]}'); background-size:cover; background-repeat: no-repeat; background-position: center center;"></div>
        <div class="card__name">${traderName}</div>
        <button class="card__more">Подробнее</button>
        <div class="card__income">
          <span class="card__income-text">
          Прибыль
          </span>
          <span class="card__income-rate">
          ${procent}
          </span>
          <span class="card__income-term">
          30д
          </span>
        </div>
        <div class="card__status">
          <span class="card__rating-text">
          топ
          </span>
          <span class="card__rating-number">
          ${1}
          </span>
          <span class="card__followers-text">
          Подписчиков
          </span>
          <span class="card__followers-number">
          ${followers}
          </span>
        </div>
        <div class="card__available-wrapper">
          <div class="card__available card__available--top">
          <span class="card__availabe-text">Доступно</span>
          <span class="card__available-number">btc 0</span>
          </div>
          <div class="card__bar-wrapper">
          <div class="card__bar">
            <div class="card__bar-progress"></div>
          </div>
          </div>
          <div class="card__available card__available--bottom">
          <span class="card__availabe-text">0 btc</span>
          <span class="card__available-number">btc 50</span>
          </div>
        </div>
      </div>
`;

// let $elem = document.createElement('div');
// $elem = template;
  const $elem = document.querySelector('.card-section');
  $elem.innerHTML += template;

}


const downloadCards = new XMLHttpRequest();


downloadCards.onreadystatechange = function() {
  if (downloadCards.status === 200) {
    console.log('DOWNLOAD CARDS IS SUCCESSFULL');
    console.log(downloadCards.responseText);
    let tradersJson = JSON.parse(downloadCards.responseText);
    downloadCards.abort();
    for(let i = 0; i< tradersJson.traders.length; i++){
      console.log(i)
      renderingTraders(tradersJson, i);
    }

  }

  if (downloadCards.status === 400) {
    console.log('DOWNLOAD CARDS IS FAILED');
  }
}


downloadCards.open('get', '/investor/traders');
downloadCards.send();

// for(let i = 0; i< tradersJson.traders.length; i++){
//   console.log(i)
//   renderingTraders(tradersJson, i);
// }


//render_traders END


//local START

  let localItem = JSON.parse(localStorage.getItem('loc'));

if(localItem.length){
  console.log(localItem)
  if(localItem === 'en'){
    localizator(ENG_LOCAL);
  }
  if(localItem === 'ru'){
    localizator(RU_LOCAL);
  }
  if(localItem === 'ch'){
    localizator(CH_LOCAL);
  }
}

function localizator(obj){

  document.querySelector('.menu-item__button--panel').childNodes[2].nodeValue = obj.asideButtons.navPanel;
  document.querySelector('.menu-item__button--trader').childNodes[2].nodeValue = obj.asideButtons.myTrader;
  document.querySelector('.menu-item__button--recharge').childNodes[2].nodeValue = obj.asideButtons.recharge;
  document.querySelector('.menu-item__button--settings').childNodes[2].nodeValue = obj.asideButtons.settings;
  document.querySelector('.menu-item__button--exit').childNodes[2].nodeValue = obj.asideButtons.exit;
  document.querySelector('.container_nav-panel__title').innerText = obj.sections.navPanel.title;
  document.querySelector('.description').innerText = obj.sections.navPanel.mainDescription;
  const $card1 = document.querySelector('.card_1');
  const $card2 = document.querySelector('.card_2');
//card_titles
  $card1.children[0].innerText = obj.sections.navPanel.step_1;
  $card2.children[0].innerText = obj.sections.navPanel.step_2;
//card_descripts
  $card1.children[1].innerText = obj.sections.navPanel.card1Description;
  $card2.children[1].innerText = obj.sections.navPanel.card2Description;
//card_buttons
  $card1.children[2].innerText = obj.sections.navPanel.card1Button;
  $card2.children[2].innerText = obj.sections.navPanel.card2Button;

// common_buttons

  document.querySelector('.buttons-section__button--trader').childNodes[2].nodeValue = obj.commonButtons.buttonMytrader;
  document.querySelector('.buttons-section__button--top').childNodes[2].nodeValue = obj.commonButtons.buttonTop;
  document.querySelector('.buttons-section__button--subscribe').childNodes[2].nodeValue =obj.commonButtons.buttonSubscription ;
  document.querySelector('.buttons-section__button--charge').childNodes[2].nodeValue = obj.commonButtons.fillingButton;
  document.querySelector('.buttons-section__button--withdraw').childNodes[2].nodeValue = obj.commonButtons.withdrawalOfFundsButton;
  document.querySelector('.account_report__title').innerText = obj.contentBlocksTitles.accountReport;
  document.querySelector('.my_trader__title').innerText = obj.commonButtons.buttonMytrader;



  //inner_buttons
  const $accountReportBlockButtons_1 = document.querySelector('.account_report__container_button');
  $accountReportBlockButtons_1.children[0].innerText = obj.innerButtons.accountReportButtons.totalBalance;
  $accountReportBlockButtons_1.children[1].innerText = obj.innerButtons.accountReportButtons.managementbalance;
  $accountReportBlockButtons_1.children[2].innerText = obj.innerButtons.accountReportButtons.profit;

  const accountReportBlockButtonsALL_2 = document.querySelectorAll('.stock_exchanges');
  accountReportBlockButtonsALL_2[1].children[0].innerText = obj.innerButtons.accountReportButtons.stockExchanges;
  accountReportBlockButtonsALL_2[0].children[0].innerText = obj.innerButtons.accountReportButtons.stockExchanges;

  const $accountReportSpinBlockButtons_1 = document.querySelector('.acc_rep__container_button');
  $accountReportSpinBlockButtons_1.children[0].innerText = obj.innerButtons.accountReportButtons.totalBalance;
  $accountReportSpinBlockButtons_1.children[1].innerText = obj.innerButtons.accountReportButtons.managementbalance;
  $accountReportSpinBlockButtons_1.children[2].innerText = obj.innerButtons.accountReportButtons.profit;
  document.querySelector('.wallet-card__update').innerText = obj.innerButtons.accountReportButtons.walletButton;

//risk_label
  document.querySelector('.my_trader__bottom').innerText = obj.innerButtons.myTraderButtons.moreButton;
  document.querySelector('.my_trader__hazard__message').children[1].innerText = obj.risk.riskTitle;
  document.querySelector('.my_trader__hazard__message').children[2].innerText = obj.risk.riskContent;
  document.querySelector('.wallet-section').children[0].children[0].innerText = obj.contentBlocksTitles.walletBlockCard;
  document.querySelector('.wallet-section').children[1].children[0].innerText = obj.contentBlocksTitles.walletBlockAdress;
  const table = document.querySelector('.history-block__content-wrapper');
  table.children[0].innerText = obj.history.historyTitle;
  table.children[1].children[0].children[0].children[0].innerText = obj.history.historyTable.day;
  table.children[1].children[0].children[0].children[2].innerText = obj.history.historyTable.sum;
  document.querySelector('.profit__description').innerText = obj.sections.myTrader.profDescript;
  document.querySelector('.top__description').innerText = obj.sections.myTrader.topDescr;
  document.querySelector('.subscribers__description').innerText = obj.sections.myTrader.subscribers;
  document.querySelector('.subscription_term__description').innerText = obj.sections.myTrader.subscrTerm;

  document.querySelectorAll('.transfer_date__button')[0].innerText = 7 +' '+ obj.commonButtons.transferButtons;
  document.querySelectorAll('.transfer_date__button')[1].innerText = 14 +' '+ obj.commonButtons.transferButtons;
  document.querySelectorAll('.transfer_date__button')[2].innerText = 21+' '+ obj.commonButtons.transferButtons;
  document.querySelector('.transfer_date__produce_subscribe').innerText = obj.commonButtons.subscribeButton;
  document.querySelector('.transfer_date__description').innerText = obj.transferDate;
  document.querySelector('.balance_copytrading__title').innerText = obj.balanceCopytrading;


  const $subscribeCost = document.querySelector('.subscribe_cost');
  $subscribeCost.children[0].childNodes[0].nodeValue = obj.subscrCost;
  $subscribeCost.children[2].children[0].innerText = obj.comission;
  document.querySelector('.subscription__title').childNodes[0].nodeValue = obj.subscribeTo + ' ';
  document.querySelector('.card__more').innerText = obj.innerButtons.myTraderButtons.moreButton;
  document.querySelector('.card__income-text').innerText = obj.sections.myTrader.profDescript;
  document.querySelector('.card__rating-text').innerText = obj.sections.myTrader.topDescr;
  document.querySelector('.card__followers-text').innerText = obj.sections.myTrader.subscribers;
  document.querySelector('.card__availabe-text').innerText = obj.available;
  document.querySelector('.adress__input_title').innerText = obj.withdrawalAddress;
  document.querySelector('.adress__button').innerText = obj.withdrawFunds;
  document.querySelector('.balance_outcontrol__title').innerText = obj.balanceNotMaining;
  document.querySelector('.conclusion__title').innerText = obj.amountWithdraw;
  document.querySelector('.conclusion__button').innerText = obj.confirm;
}

document.querySelector('.languages__select').addEventListener('change', (e)=>{
  let localKey = JSON.stringify(e.target.value);

localStorage.setItem('loc', localKey);
if (e.target.value === 'en') {
  localizator(ENG_LOCAL);
}
if (e.target.value === 'ru') {
  localizator(RU_LOCAL);
}
if (e.target.value === 'ch') {
  localizator(CH_LOCAL);
}


});

// local END


// const managePanelButton = document.querySelector('.menu-item__button--panel');
// console.log(managePanelButton);

// managePanelButton.state = false;

const $asideMenu = document.querySelector('.aside__menu');
const asideMenu = [...document.querySelectorAll('.menu-item__button')];
// console.log(asideMenu);

const walletUpdateButton = document.querySelector('.wallet-card__update');
console.log(walletUpdateButton);
walletUpdateButton.addEventListener('click', (e) => {
  const xhr = new XMLHttpRequest();

const cash = document.querySelector('.cash');
console.log(cash);
const btc = document.querySelector('.btc');

  xhr.onreadystatechange = function() {
    if (xhr.status === 200) {
      console.log('success');
      console.log(xhr.responseText);
      let responseCash = JSON.parse(xhr.responseText)["balance"];
      console.log(responseCash);
      walletCardRate.dataset.count = responseCash;
      cash.textContent = `${(responseCash * 3991.56).toFixed(3)} $`;
      btc.textContent = `${responseCash} BTC`;
      // cash.textContent = String(responseCash);
      // walletCardRate.dataset.count = testJsonWalletFormServer['balance'];
      // конвертация в доллары в карточке ШАГ 1
      // cash.textContent = ${testJsonWalletFormServer['balance'] * 3991.56}$;
      // btc.textContent = ${testJsonWalletFormServer['balance']} BTC;
    }
    if (xhr.status === 400) {
      console.log('ERROR WALLET');
    }
  }
  xhr.open('get','investor/balance/1');
  xhr.send();
});

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
console.log(walletWindow);
const historyBlock = document.querySelector('.history-block');

const walletDisplay = document.querySelector('.wallet-block__wallet-key');
console.log(walletDisplay);

let walletAddress;
//
const xhr = new XMLHttpRequest();

xhr.onreadystatechange = function() {
  console.log(xhr.readyState);
  if (xhr.readyState === 4) {
    console.log('request is complited');
  }
  if (xhr.status === 200) {
    console.log('request is successful');
    console.log(xhr.responseText);
    walletAddress = JSON.parse(xhr.responseText);
    console.log(walletAddress);
    walletDisplay.value = walletAddress["address"];

  }
  if (xhr.status === 404) {
    console.log('ERROR');
  }
}


xhr.open("get",'investor/address/1', true);

// xhr.setRequestHeader("Content-type","application/json");
xhr.send();


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

const buttonsTopPanelStateReset = (exeptionArr) => {

  topPanelButtonsCollection.forEach(item => {
    item.isActive = false;

  exeptionArr.forEach((item) => {
    item.classList.remove('buttons-section__button--active');
  item.childNodes[1].classList.remove('buttons-section__icon--active');
});

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


      traderWindow.classList.add('hidden');
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
      // testFuntion();

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

