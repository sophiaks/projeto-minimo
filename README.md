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

## Rodando o aplicativo
Após instalar o *Android Studio*, para acessar o projeto, basta abrir o *Android Studio*, ir em `Open an existing Android Studio project` (caso esteja rodando o aplicativo pela primeira vez), ou *File → Open...*, selecione o diretório projeto-minimo/projeto-minimo
