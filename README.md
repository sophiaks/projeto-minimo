# projeto-minimo
Este repositório contém o projeto final da matéria Desenvolvimento Colaborativo Agil, dos alunos do Insper, em parceria com a empresa Mínimo. Esse projeto visa criar um aplicativo Android para auxiliar a empresa Mínimo em um projeto com seu cliente Programa Semente.

## Como executar
### Requerimentos
- [Android Studio](https://developer.android.com/studio)

## Instalação do Android Studio
  A instalação pode variar dependendo do sistema operacional!
  
  Inicialmente, deve-se escolher apenas a opção de instalar o Android Studio, sem necessidade de baixar o **Virtual Device**.
  
  Em seguida, escolha a instalação **Custom**. Em *SDK Components Setup*, desmarque novamente a opção *Virtual Device* e, **se estiver em Windows ou macOS**, marque a opção *Intel HAXM*. Essa opção não existe em **Linux**.
  
  Quando a personalização terminar, o processo de configuração vai começar. **Se estiver em Windows ou macOS**, esse processo de configuração deve terminar com a mensagem *Intel HAXM installed successfully*. Por motivos variados, a instalação do *Intel HAXM* pode falhar. Nesse caso, baixe e rode o [instalador oficial](https://github.com/intel/haxm/releases/tag/v7.6.1), antes de continuar.
  
  Para conseguir rodar o aplicativo, vamos precisar fazer o setup do emulador. Na pagina inicial, clique em *Configure* no canto inferior direito, e em seguida selecione **AVD Manager**. Isso vai abrir a lista de emuladores, que no caso deverá estar vazia. Se não estiver, clique na seta de cada dispositivo para deletá-lo. 
  
  Você deve então criar um dispositivo virtual. Para isso, clique no botão *Create Virtual Device*. Na página *Select Hardware*, selecione a categoria *Phone* e escolha o hardware *Pixel 2*. Na próxima página, *System Images*, vá na categoria *x86 Images* e selecione a opção *Pie 28 x86_64 Android 9.0 (Google APIs)*. Você pode concluir o setup. A primeira vez que você rodar o emulador pode demorar, portanto se quiser já pode rodá-lo da página do *AVD Manager* e ver se ele funciona sem problemas. 
  
  Se você estiver utilizando o **Ubuntu**, pode ser que ocorra o erro *KVM is required to run this AVD*. Caso isso ocorra, tente:
  ```
  sudo apt-get install qemu-kvm
  sudo adduser <USERNAME> kvm
  ```
  Onde `<USERNAME>` é o nome de usuário que você usa no Ubuntu. O sistema deve então ser reiniciado.

## Rodando o aplicativo
Após instalar o *Android Studio*, para acessar o projeto, basta abrir o *Android Studio*, ir em `Open an existing Android Studio project` (caso esteja rodando o aplicativo pela primeira vez), ou *File → Open...*, selecione o diretório *projeto-minimo/projetominimo*.

Feito isso, cheque se o *Android Studio* está instalando alguma dependência (muito comum no início de um projeto), essa checagem pode ser feita olhando na parte de baixo do aplicativo, analisando se há algum processo em andamento. Após a instalação das dependências, pode-se rodar o aplicativo clicando no **botão verde** na parte de cima, ao lado do *Pixel 2 API 28*
