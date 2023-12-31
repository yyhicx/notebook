# 计算机网络技术概论

1.  [计算机网络的起源和发展](#计算机网络的起源与发展)
2.  [计算机网络的分类](#计算机网络的分类)
3.  [计算机网络结构](#计算机网络结构)
4.  [计算机网络的硬件和软件设备](#计算机网络的硬件和软件设备)
5.  [计算机网络的性能指标](#计算机网络的性能指标)
6.  [计算机网络的功能和应用](#计算机网络的功能和应用)
7.  [计算机网络的标准化组织](#计算机网络的标准化组织)

## 计算机网络的起源与发展

计算机网络的概念：

*   从资源共享的角度定义计算机网络：计算机网络是以能够相互共享资源的方式互联起来的自治计算机系统的集合。

计算机网络的发展：

*   远程联机系统：20世纪中期，人们将计算机技术和通信技术相结合，形成了以单台计算机为中心的远程联机系统。
*   ARPANET：
    *   20世纪60年代，ARPA网，标志着计算机网络的兴起。
    *   ARPANET采用分组交换技术。
    *   ARPANET采用TCP/IP模型，逐渐形成了现在的Internet的基本框架。
    *   1983年，美国国防部将ARPANET分成两个独立的网络ARPANET和MILNET。
    *   ARPANET的意义和作用：完成对计算机网络的定义和分类方法的研究。提出了资源子网和通信子网的结构概念。提出并实现了分组交换技术。采用了层次结构的网络体系结构和研究方法。促进了TCP/IP模型的研究与应用。为Internet的形成和发展奠定了基础。
*   三网合一：Internet的发展使得计算机网络、电信网络、广播电视网络相互融合，形成了三网合一的覆盖各种数据业务的综合信息网络。

## 计算机网络的分类

按照网络覆盖范围划分：局域网，城域网，广域网，互联网。

*   局域网LAN：
    *   范围在几百米到几千米的办公楼群或校园内的计算机等数据终端设备相互连接所构成的网络。实现资源共享和数据通信。
    *   特点：早期局域网使用共享信道，数据传输率高，低延迟，误码率低。
    *   新型的局域网的数据传输速率可以高达10Gbps或100Gbps。
    *   传统的局域网的拓扑结构一般采用总线型结构和环形结构。
    *   新型的局域网的拓扑结构一般采用星型拓扑结构。
    *   目前市场上的局域网产品遵循IEEE802.3协议标准的以太网Ethernet。
    *   无线局域网WLAN正在取代有限局域网，遵循的标准有802.11系列标准。
*   城域网MAN：
    *   目前采用的技术和局域网类似，覆盖范围可以是一座城市。城域网的拓扑结构一般为总线型，包含1到2根电缆，有IEEE802.6标准。
    *   目前很多城域网采用以太网技术。
*   广域网WAN：
    *   覆盖范围可以达到一个或几个国家。采用交换技术，将分布在各地的主机或局域网连接起来。
    *   交换技术：早期是X.25，现在是帧中继和ATM。
    *   交换设备：路由器和交换机。
    *   传输介质：光纤，具有较高的通信速率。
*   互联网：
    *   将世界各地的局域网和广域网通过一定的方式连接起来，构成了互联网。
    *   最常见的形式是多个局域网通过广域网连接起来，由于物理结构、协议和标准各不相同。所以需要通过“网关”设备进行转换。
    *   Internet（因特网）是常见的互联网形式。

按照网络拓扑结构划分：总线型网络，环形网络，星型网络，树形网络，网状网络。

*   总线型网络：
    *   所有数据终端均连接到一条通信线路上，同一时刻只能有两个网络结点进行通信。
    *   优点：节省通信线路，结构简单，价格便宜。
    *   缺点：不支持多点通信；若线路故障，整个网络不能正常运行。
*   环形网络：
    *   将所有的数据终端连接到一个闭合的环形通信线路上。
    *   优点：网络中的传输延迟是固定的，控制机制简单。
    *   缺点：网络中一个节点出现故障，整个网络不能通信。
*   星型网络：
    *   以一台数据设备（交换机）作为中心处理系统，其他入网设备均与中心处理机通过通信线路相连，其他节点之间不能直接通信，必须通过中心处理机进行转发。
    *   优点：结构简单，局部故障不影响全网通信。
    *   缺点：中心处理机性能要求高，线路利用率低。
*   树形网络：
    *   将原先用通信线路直接相连的网络节点通过多级处理机进行分层相连，每一层设置一个中心处理机，负责与它相连的入网设备通信。与星型网络相比节省通信线路成本。
*   网状网络：
    *   网络中的数据终端可以与其他设备根据需要任意相连，两个网络节点之间可以任意通信，也可以通过其他网络结点进行转接。
    *   优点：局部故障不影响整个网络，可靠性强。
    *   缺点：网络结构复杂，不易管理和控制。

按照交换方式划分：电路交换网络，报文交换网络和分组交换网络。

*   电路交换网络：电路交换网络是一种传统的通信方式，其中在通信会话期间为两个通信终端建立了一条专用的物理连接或电路。一旦连接建立，通信终端之间的数据可以连续地传输，就好像它们之间有一条独占的通信通道。这种方法适用于持续时间较长的通信，例如电话通话。然而，由于连接是独占的，即使在静默时段也会占用带宽，因此效率相对较低。
*   报文交换网络：报文交换网络是一种在通信终端之间传递整个数据报文的方式。每个报文都被独立地封装并传输，不需要建立持续的连接。这使得多个通信终端可以共享网络资源，因为它们不会独占连接。电子邮件就是报文交换网络的一个例子，其中电子邮件被划分为报文并通过网络传递。
*   分组交换网络：分组交换网络是一种现代通信方式，数据被划分为小的数据包（分组）并通过网络传输。这些分组可以独立地从源传输到目的地，它们可能会选择不同的路径甚至以不同的顺序到达。这种方式更加灵活高效，适用于互联网和许多其他计算机网络。TCP/IP协议族在分组交换网络中扮演了重要角色。

按照传输介质的类型可以分为有线网络和无线网络：

*   有线网络的传输介质：双绞线，同轴电缆，光纤。
*   无线网络利用无线电磁波进行传输。
*   目前常见的无线网有无线局域网WLAN，移动通信网，物联网等。

按照所有权性质的不同可分为公用网和专用网。

按照网络中的逻辑功能划分为通信子网和资源子网：

*   通信子网：负责完成网络数据的传输、转发、交换和路由等通信任务。通信控制处理机、通信线路和其他网络设备组成。
*   资源子网：负责网络的数据处理业务，向用户提供资源和服务。主机、终端、联网的外部设备、软件和信息资源组成。

按照网络的传输技术划分为广播式网络和点对点式网络：

*   广播式网络：网络中的计算机共享一条公共信道，任何一台计算机发送数据，其他计算机都可以收到这个数据。典型的广播式网络是传统的以太网。
*   点对点式网络：网络中建立通信的两台计算机之间由一条物理信道相连，数据分组由源计算机直接或者经过转发到目的计算机。典型的点对点式网络是因特网。

## 计算机网络结构

计算机网络结构可以划分为三个主要部分：网络边缘（Network Edge），接入网络（Access Network），以及网络核心（Network Core）。这些部分共同构成了现代计算机网络的基本架构。

*   网络边缘（Network Edge）：网络边缘是指位于计算机网络边缘的所有用户设备，包括个人计算机、智能手机、服务器等。这些设备是网络的终端节点，它们通过网络进行通信和数据交换。网络边缘是网络中最直接与用户交互的部分，包括终端设备、终端用户之间的应用程序、以及用户使用的各种服务和资源。
*   接入网络（Access Network）：接入网络是连接用户设备（位于网络边缘）与网络核心之间的中间层。这一层负责将用户设备连接到更大范围的网络，通常通过各种连接技术，如以太网、无线网络（Wi-Fi、蜂窝网络等）或光纤等。接入网络的设计和性能对于用户体验和数据传输速度具有重要影响。
*   网络核心（Network Core）：网络核心是网络的中心部分，它负责在不同的接入网络之间转发数据。网络核心采用分组交换技术（如IP路由）来实现数据包在网络中的传输。在网络核心中，存在大量的路由器和交换机，它们协调数据包的转发，确保数据能够有效地从源端传输到目的地。

这三个部分共同构成了计算机网络的基本结构，它们协同工作以实现用户设备之间的通信和数据交换。网络边缘提供了用户接口和应用程序，接入网络将用户连接到网络核心，而网络核心则负责高效地传输数据包。整个结构的设计和性能优化对于构建可靠、高效的计算机网络至关重要。

## 计算机网络的硬件和软件设备

网络设备分类：

*   根据设备的物理性质，计算机网络设备可以分为硬件设备和软件设备：
    *   硬件设备：计算机、服务器、交换机、路由器和通信介质。
    *   软件设备：网络协议、网络操作系统和应用程序。
*   根据设备在网络中的逻辑位置不同，计算机网络设备可以分为终端设备和网络中间设备：
    *   终端设备：台式机、服务器、笔记本、智能手机和打印机。
    *   网络中间设备：交换机、路由器和通信线路。

基本的计算机网络设备：终端计算机、交换机、路由器、网络协议、网络操作系统和传输介质。

*   终端计算机：
    *   终端计算机是用户访问和使用计算机网络的界面。可以从网络中获取信息和资源，也可以提供信息和资源。
    *   一般将从网络中获取信息和服务的终端计算机称为客户机。
    *   提供信息和资源服务的终端计算机称为服务器。
*   交换机Switch：
    *   交换机是交换式网络的核心设备、负责网络内部数据的调度和转发。
    *   交换机有若干物理接口与计算机相连（RJ45接口），交换机内部有地址表（计算机地址（MAC地址）与端口的对应关系）。
    *   工作过程：交换机收到发送过来的数据分组（帧），查地址表，将数据分组转发到对应的物理接口，从而到达目的主机。
    *   根据处理的协议层次不同分为二层交换机（数据链路层，处理帧）和三层交换机（网络层，处理IP分组）。
*   路由器Router：
    *   路由器连接IP网络中的不同类型的网络，为网络中的数据分组进行路由选择和数据转发。
    *   路由器实现不同网络之间的数据转发，交换机实现的是同一网络内部数据的存储转发。
    *   工作过程：路由器收到发送过来的IP分组，根据目的IP地址查路由表，选择合适的接口转发。
    *   路由表可以由路由算法得到。
    *   路由表如果是固定不变的，对应的路由算法称为静态路由算法。
    *   路由表根据网络变化不断更新，对应的路由算法称为动态路由算法。
    *   路由器根据管辖的范围和处理能力不同，可分为核心路由器、汇聚路由器和接入路由器。
*   网络协议：
    *   为进行网络中的数据通信而制定的规则、标准和约定称为网络协议。
    *   网络协议包含三个要素：
        *   语法：规定数据和控制信息的分组结构或格式。
        *   语义：规定进行通信需要发出的控制信息、完成的操作动作和响应。
        *   时序：规定交换信息的顺序以及如何匹配或适应彼此的速度。
*   网络操作系统：
    *   网络操作系统除了具有操作系统的功能外，还要具有管理网络通信和提供网络服务的功能。（操作系统有五大功能：处理器管理、作业管理、存储器管理、设备管理和文件管理）。
    *   网络操作系统可分为：任务型和通用型。
        *   任务型：针对某一特殊网络应用而设计，如Cisco路由器的专用操作系统。
        *   通用型：提供一般性的网络服务功能，如Windows Server 2008，Linux等。
*   传输介质：
    *   有线介质和无线介质。
    *   常用的有线介质：双绞线、同轴电缆和光纤。

## 计算机网络的性能指标

定量的性能指标：速率、带宽、端到端延迟和吞吐量。

*   速率：发送速率和传输速率。
    *   发送速率指在终端或网络中间结点，计算机设备每秒向网络中发送多少比特数据，反映的是网络设备的性能，通常采用比特每秒（bit/s或bps）衡量。如千兆以太网指的是发送速率为1000M比特每秒的以太网。
    *   传输速率指数据信号在传输线路上每秒能传播多少千米，单位为千米每秒，反映的是信号及信道的性能。如光在真空中传输速率是3.0×10<sup>8</sup>米/秒。
*   带宽：
    *   在模拟信道中，带宽包括信道带宽和信号传输带宽。
        *   信道带宽，信道固有特性，只与信道介质有关，是信道中能通过模拟信号的最高频率和最低频率的差值，单位赫兹。如电话线信道带宽可达到2MHz。
        *   信号传输带宽指某种通信业务信号频率的最高分量和最低分量之间的差值，单位赫兹。如语音信号使用300～3400Hz的频段，语音信号传输带宽为3100Hz。
    *   数字通信系统，特别是计算机网络中，带宽表示通信线路所能传送数据的能力，即单位时间内从网络中一点到另一点所能通过的最高数据量，单位比特每秒。
*   端到端延迟：表示数据分组从网络中一个端点到另一个端点所花费的时间，包括发送延迟，传播延迟，处理延迟，排队延迟。
*   吞吐量：在单位时间内通过某个网络的数据量。吞吐量是衡量现实网络能够通过多少数据量，受网络带宽和网络的额定速率的限制。

非定量的性能指标：

*   服务器质量QoS指利用各种基础技术，为指定的网络通信提供更好的服务能力，是网络自身预防拥塞和从拥塞中恢复的一种安全机制。
*   可靠性：能否长时间无故障运行或者能及时从故障中恢复。
*   可扩展性：设计网络之初，需要考虑网络的可扩展性。
*   安全性：具有抵御和防范各种风险和威胁的能力。
*   标准化：网络产品所遵循的标准。
*   成本：设计、建设、使用网络的费用。

额外拓展：8088处理器的数据总线为8位，典型的时钟频率是5MHz，即每个时钟周期是1/5MHz=0.2×10<sup>-6</sup>秒，8088需要四个时钟周期构成一个总线周期，实现一次8位数据传送，故8088处理器的总线带宽为8/(4×0.2×10<sup>-6</sup>)b/s=10Mb/s。

## 计算机网络的功能和应用

计算机网络的功能：实现数据通信；提供资源共享（硬件资源和软件资源）；提高计算机系统的可靠性；进行分布式处理；对分散对象提供集中控制和管理。

计算机网络的应用：

*   最初计算机网络应用：远程登录Telnet，文件传输服务FTP，电子邮件E-mail，工业控制等。
*   1991年，英国人伯纳斯·李发明了基于超文本传输协议（HTTP）。HTTP的万维网WWW，使互联网得到迅猛的发展。
*   搜索引擎，即时通讯，电子商务，社交网络。
*   移动互联网，物联网（IoT），云计算，大数据等网络形态和模式。

## 计算机网络的标准化组织

国际标准化组织ISO：

*   一个全球的非政府组织，总部在瑞士日内瓦，任务是推动各个行业的国际标准化活动。
*   开放系统互联参考模型OSI，将网络分为7层。

国际电信联盟ITU：负责协调世界各国电信业务的国际组织。

美国电子工业协会：由美国半导体、通信和计算机等系统和设备制造企业共同成立的行业协会。

电气和电子工程师协会IEEE：制定了一系列局域网标准，IEEE802系列标准。
