<!-- TEMPLATE DO README: https://github.com/othneildrew/Best-README-Template/blob/master/README.md -->

<br />
<p align="center">
  <h3 align="center">Eventos - Desafio Sicredi/Soft Design</h3><p align="center">
    <br />
  </p>
</p>

## API de Eventos do Desafio

A API possui uma rota que retorna uma lista de eventos para realizar check-in

### Eventos

O evento tem o seguinte formato:


```json
 {
    "people": [],
    "date": 1534784400000,
    "description": "O Patas Dadas estará na Redenção, nesse domingo, com cães para adoção e produtos à venda!\n\nNa ocasião, teremos bottons, bloquinhos e camisetas!\n\nTraga seu Pet, os amigos e o chima, e venha aproveitar esse dia de sol com a gente e com alguns de nossos peludinhos - que estarão prontinhos para ganhar o ♥ de um humano bem legal pra chamar de seu. \n\nAceitaremos todos os tipos de doação:\n- guias e coleiras em bom estado\n- ração (as que mais precisamos no momento são sênior e filhote)\n- roupinhas \n- cobertas \n- remédios dentro do prazo de validade",
    "image": "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
    "longitude": -51.2146267,
    "latitude": -30.0392981,
    "price": 29.99,
    "title": "Feira de adoção de animais na Redenção",
    "id": "1"
  }
  ```
    
### Checkin

Pra realizar checkin, temos um **POST** com o seguinte formato:
\
  ```json
{ "eventId": "1", "name": "Otávio", "email": "otavio_souza@..." }
  ```


## Telas


### Informações do Usuário
Tela para o usuário inserir o seu nome e email que será usado para realizar o checkin.
Ambas as informações são validadas antes de ser passado para a próxima tela:
\
<img src="https://i.imgur.com/sS3Y66R.png" width="200" height="424"/>

### Tela Inicial
A tela principal do aplicativo exibe a lista de eventos disponíveis.

\
<img src="https://i.imgur.com/RsBaE5B.png" width="200" height="424"/>          
     

### Evento

Na tela do evento, é exibida o título, descrição, data, um botão para compartilhar o evento e um botão para realizar o checkin:

\
<img src="https://i.imgur.com/6peGumk.png" width="200" height="424"/>  <img src="images/share.gif" width="200" height="424"/>     

### Checkin

Para realizar o checkin só basta clicar no botão, em seguida é feita o POST a API, mostrando a mensagem de sucesso ou falha:

\
<img src="https://i.imgur.com/6peGumk.png" width="200" height="424"/>        

## Arquitetura
A arquitetura usada no projeto é a **Model View ViewModel - MVVM**, onde dentro da aplicação as camadas são divididas em *Model*, *View* e *ViewModel*

<img src="https://i.stack.imgur.com/Swv8V.png" />  
  

1. **View:** Todas a parte de Interface Gráfica e interação com usuário.
2. **ViewModel:** Faz a ligação das as operações entre a View e a camada Model.
3. **Model:** Lógica envolvida nos domínios da aplicação (Event, Checkin...)


## Injeção de dependências

Para injeção de dependências foi usada o a biblioteca **Koin**, para que seja injetado os módulos como *ViewModel*, *Repositório*, *Retrofit* e etc...
  
## Bibliotecas Usadas

Algumas bibliotecas externas foram usadas para ajudar no desenvolvimento:

* [Retrofit](https://square.github.io/retrofit/) - Para realizar chamadas a API.
* [Rounded Image View](https://github.com/vinc3m1/RoundedImageView) - Imageview com bordas arredondadas.
* [Skeleton](https://github.com/ethanhua/Skeleton) - Animações para loading.
* [Glide](https://github.com/bumptech/glide) - Usada para inserir imagens com qualidade em Imageview.
* [Lottie](https://github.com/airbnb/lottie-android) - Usada para animações. (Animações: [Lottie Files](http://lottiefiles.com/))
