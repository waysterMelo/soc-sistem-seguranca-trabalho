import React, { useState } from 'react';
import {
    Search,
    Trash2,
    Plus,
    Clock,
    Check,
    RefreshCw,
    Paperclip,
    Bold,
    Italic,
    Underline,
    Strikethrough,
    AlignLeft,
    AlignCenter,
    AlignRight,
    List,
    Link2,
    Image as ImageIcon,
    AlertTriangle,
    Download,
    ChevronDown
} from 'lucide-react';


// --- DADOS DE EXEMPLO ---
const aparelhosCadastradosData = [
    { id: 1, descricao: 'Decibelímetro Digital', modelo: 'DEC-460', dataCalibracao: '15/10/2025' },
    { id: 2, descricao: 'Bomba de Amostragem', modelo: 'BAM-123', dataCalibracao: '01/02/2026' }
];
const profissionaisData = ['MEDICO TESTE WAYSTER'];
const empresasContratantesData = [{empresa: 'EMPRESA CONTRATANTE EXEMPLO LTDA', unidade: 'UNIDADE CENTRAL'}];


// --- COMPONENTES REUTILIZÁVEIS ---

// Secção do formulário com título
const FormSection = ({ title, children, className }) => (
    <div className={`bg-white p-6 rounded-lg shadow-md ${className}`}>
        {title && <h3 className="text-xl font-semibold text-gray-800 border-b border-gray-200 pb-4 mb-6">{title}</h3>}
        {children}
    </div>
);

// Input com botões de ação
const InputWithActions = ({ placeholder, value, actions, className='' }) => (
    <div className="relative flex items-center">
        <input
            type="text"
            placeholder={placeholder}
            defaultValue={value}
            className={`w-full py-2 pl-4 pr-20 border border-gray-300 rounded-md focus:outline-none transition-colors bg-white focus:ring-2 focus:ring-blue-500 ${className}`}
        />
        <div className="absolute right-0 flex">{actions}</div>
    </div>
);

// Simulação de um Editor de Texto Rico
const RichTextEditor = ({ initialContent = '', rows = 8 }) => (
    <div className="border border-gray-300 rounded-lg">
        <div className="flex flex-wrap items-center p-2 border-b border-gray-200 bg-gray-50 rounded-t-lg">
            <select className="text-sm border-gray-300 rounded-md mr-2"><option>Normal</option></select>
            <div className="flex items-center space-x-1">
                {[Bold, Italic, Underline, Strikethrough, AlignLeft, AlignCenter, AlignRight, List, Link2, ImageIcon].map((Icon, index) => (
                    <button key={index} type="button" className="p-2 text-gray-600 rounded-md hover:bg-gray-200"><Icon size={16}/></button>
                ))}
            </div>
            <div className="flex items-center ml-auto space-x-1">
                <button type="button" className="p-2 text-blue-600 rounded-md hover:bg-gray-200"><Paperclip size={16}/></button>
                <button type="button" className="p-2 text-green-600 rounded-md hover:bg-gray-200"><RefreshCw size={16}/></button>
            </div>
        </div>
        <textarea defaultValue={initialContent} rows={rows} className="w-full p-4 focus:outline-none rounded-b-lg"></textarea>
    </div>
);

// Botão de Aba
const TabButton = ({ label, isActive, onClick, hasDot = false }) => (
    <button type="button" onClick={onClick} className={`relative px-4 py-3 -mb-px text-sm font-semibold whitespace-nowrap transition-colors border-b-2 ${isActive ? 'border-blue-600 text-blue-600' : 'border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700'}`}>
        {label}
        {hasDot && <span className="absolute top-2 right-2 block h-2 w-2 rounded-full bg-red-500"></span>}
    </button>
);


// --- SUB-COMPONENTES PARA O CONTEÚDO DAS ABAS ---
const TabAgentesNocivos = () => {
    const [subTab, setSubTab] = useState('agentes');
    return (
        <div className="space-y-4">
            <div className="flex justify-center">
                <div className="flex items-center rounded-lg bg-gray-200 p-1">
                    <button onClick={() => setSubTab('agentes')} className={`px-4 py-1 text-sm rounded-md ${subTab === 'agentes' ? 'bg-green-500 text-white' : 'text-gray-600'}`}>Agentes Nocivos</button>
                    <button onClick={() => setSubTab('riscos')} className={`px-4 py-1 text-sm rounded-md ${subTab === 'riscos' ? 'bg-green-500 text-white' : 'text-gray-600'}`}>Riscos</button>
                </div>
            </div>
            {subTab === 'agentes' && (
                <div>
                    <p className="text-sm text-gray-600 mb-2">Agentes nocivos na qual a função está exposta</p>
                    <div className="flex items-center gap-2 mb-4">
                        <input type="checkbox" id="no-insalubres" className="h-4 w-4 rounded text-blue-600" />
                        <label htmlFor="no-insalubres" className="text-sm">Confirmo que as atividades desenvolvidas neste ambiente ou por estes profissionais não são consideradas insalubres.</label>
                    </div>
                    <button className="flex items-center gap-2 w-full justify-center py-2 px-4 rounded-md bg-yellow-400 text-gray-800 hover:bg-yellow-500">
                        <Download size={16}/>
                        Recuperar Agentes Nocivos da Função
                    </button>
                </div>
            )}
            <div className="bg-red-50 border-l-4 border-red-400 text-red-700 p-4 rounded-r-lg">
                <p>Para criar um LTIP é necessário ao menos um agente nocivo ou risco trabalhista vinculado a função.</p>
            </div>
        </div>
    );
}

const AnexoItem = ({ title }) => {
    const [isOpen, setIsOpen] = useState(true);
    return (
        <div className="border rounded-md">
            <div className="flex justify-between items-center p-3 bg-gray-50 rounded-t-md">
                <button type="button" onClick={() => setIsOpen(!isOpen)} className="flex items-center gap-2 text-blue-600 font-semibold text-left">
                    <ChevronDown size={20} className={`transition-transform flex-shrink-0 ${isOpen ? 'rotate-180' : ''}`} />
                    {title}
                </button>
                <button type="button" className="text-red-500 hover:text-red-700 ml-4"><Trash2 size={16}/></button>
            </div>
            {isOpen && (
                <div className="p-4 border-t">
                    <label className="text-sm font-medium text-gray-600">Avaliação</label>
                    <RichTextEditor />
                </div>
            )}
        </div>
    )
}

const TabAtividadesPericulosas = () => (
    <div className="space-y-6">
        <p className="text-sm text-gray-600">Atividades e Operações Periculosas no qual a função está exposta.</p>
        <div className="flex items-center gap-2">
            <input type="checkbox" id="no-periculosas" className="h-4 w-4 rounded text-blue-600" />
            <label htmlFor="no-periculosas" className="text-sm">Confirmo que as atividades desenvolvidas neste ambiente ou por estes profissionais não são consideradas periculosas.</label>
        </div>
        <div>
            <label className="text-sm font-medium text-gray-600">Anexos pertencentes a NR-16 - ATIVIDADES E OPERAÇÕES PERIGOSAS</label>
            <InputWithActions
                placeholder="Selecione um ou mais anexos da NR-16..."
                actions={
                    <button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600 rounded-r-md"><Search size={18}/></button>
                }
            />
        </div>
        <div className="space-y-4">
            <AnexoItem title="ANEXO 2 - ATIVIDADES E OPERAÇÕES PERIGOSAS COM INFLAMÁVEIS" />
        </div>
    </div>
);


const TabAparelhos = () => (
    <div className="space-y-6">
        <div>
            <label className="text-sm font-medium text-gray-600">Pesquisar Aparelho</label>
            <InputWithActions
                placeholder="Digite para pesquisar um aparelho..."
                actions={
                    <>
                        <button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button>
                        <button type="button" className="p-2.5 text-white bg-blue-600 hover:bg-blue-700 rounded-r-md"><Plus size={18}/></button>
                    </>
                }
            />
        </div>
        <div>
            <label className="text-sm font-medium text-gray-600">Aparelhos Vinculados</label>
            <div className="mt-2 border rounded-md overflow-x-auto">
                <table className="min-w-full divide-y divide-gray-200 text-sm">
                    <thead className="bg-gray-50">
                    <tr>
                        <th className="px-4 py-2 text-left font-medium text-gray-500 uppercase">Descrição</th>
                        <th className="px-4 py-2 text-left font-medium text-gray-500 uppercase">Modelo</th>
                        <th className="px-4 py-2 text-left font-medium text-gray-500 uppercase">Data da Calibração</th>
                        <th className="px-4 py-2 text-left font-medium text-gray-500 uppercase">Ações</th>
                    </tr>
                    </thead>
                    <tbody className="bg-white divide-y divide-gray-200">
                    {aparelhosCadastradosData.map((aparelho) => (
                        <tr key={aparelho.id}>
                            <td className="px-4 py-3 font-medium text-gray-800">{aparelho.descricao}</td>
                            <td className="px-4 py-3 text-gray-600">{aparelho.modelo}</td>
                            <td className="px-4 py-3 text-gray-600">{aparelho.dataCalibracao}</td>
                            <td className="px-4 py-3">
                                <button type="button" className="text-red-500 hover:text-red-700">
                                    <Trash2 size={16}/>
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    </div>
);

// --- COMPONENTE PRINCIPAL ---
export default function CadastrarLTIP() {
    const [activeTab, setActiveTab] = useState('capa');

    // Objeto que mapeia o ID da aba para seu conteúdo ou componente.
    // Isso garante que o conteúdo correto seja sempre encontrado.
    const tabContents = {
        capa: <RichTextEditor initialContent="Crie uma capa personalizada para seu laudo."/>,
        introducao: <RichTextEditor initialContent={`O presente documento representa o Laudo Técnico de Insalubridade e Periculosidade da empresa, elaborado com o objetivo de avaliar as condições laborais a que os colaboradores estão expostos no exercício de suas atividades. A análise minuciosa das áreas de trabalho, processos produtivos, agentes físicos, químicos e biológicos presentes no ambiente laboral visa identificar possíveis situações de risco que possam comprometer a saúde, a integridade física e a qualidade de vida dos trabalhadores.\n\nEste laudo foi elaborado em conformidade com as Normas Regulamentadoras (NRs) estabelecidas pelo Ministério do Trabalho e Emprego, bem como com as diretrizes e princípios técnicos da segurança e saúde ocupacional. A sua realização contou com a participação de profissionais especializados e qualificados, visando garantir a precisão das avaliações realizadas e a imparcialidade das conclusões apresentadas.`} />,
        objetivo: <RichTextEditor initialContent={`Este laudo objetiva avaliar as atividades exercidas pelos trabalhadores cuja função denominada é, para fins de constatação de exposição a agentes insalubres contidos na NR-15 e seus anexos e / ou periculosos enquadrados na NR-16. Os enquadramentos terão como norteadores normativos portanto, as Normas Regulamentadoras 15 e 16 do Ministério do Trabalho e Emprego da Portaria 1103214, de 08 de junho de 1978.\n\nO presente trabalho servirá como ponto principal para que o setor de Recursos Humanos possa enfim deliberar ou não sobre os pagamentos desses adicionais.`} />,
        definicoes: <RichTextEditor initialContent={`Para o bom entendimento do laudo é necessário que as definições, símbolos e abreviaturas abaixo sejam devidamente compreendidas.\n\nNível de Ação: Situação em que o valor obtido por meio de avaliação quantitativa está próximo do limite de tolerância, porém ainda está abaixo deste.\n\nVCI: Abreviação para Vibração de Corpo Inteiro.\n\nVMB ou VMS: Siglas para Vibração de Mãos e Braços ou Vibração de Membros Superiores.`} />,
        metodologia: <RichTextEditor initialContent={`A metodologia adotada para a elaboração do presente laudo teve como base o levantamento das atividades e operações executadas pelos trabalhadores da empresa. Foram conduzidas inspeções nos ambientes de trabalho, observação das atividades desempenhadas, entrevistas informais com os trabalhadores e seus superiores hierárquicos, além do levantamento de documentos, identificação de máquinas, equipamentos, matérias-primas e insumos utilizados.\n\nAs metodologias de avaliações, seja qualitativa ou quantitativa, estão alinhadas com as Normas Regulamentadoras do Ministério do Trabalho, Normas de Higiene Ocupacional (NHO) da Fundacentro e, quando aplicável, as diretrizes da Conferência Norte-Americana de Higienistas Industriais Governamentais (ACGIH).`} />,
        bibliografia: <RichTextEditor initialContent={"Liste aqui as referências bibliográficas utilizadas na elaboração deste laudo, como normas técnicas, livros e artigos científicos."} />,
        aparelhos: <TabAparelhos />,
        identificacao_local: <RichTextEditor initialContent={"Descreva aqui as características do local de trabalho avaliado, incluindo layout, iluminação, ventilação e outros aspectos relevantes."} />,
        agentes: <TabAgentesNocivos />,
        atividades_periculosas: <TabAtividadesPericulosas />,
        conclusao: <RichTextEditor initialContent={"Apresente aqui a conclusão final do laudo, resumindo os principais achados e se há ou não caracterização de insalubridade ou periculosidade."} />,
    };

    const tabLabels = [
        { id: 'capa', label: 'Capa' },
        { id: 'introducao', label: 'Introdução' },
        { id: 'objetivo', label: 'Objetivo' },
        { id: 'definicoes', label: 'Definições' },
        { id: 'metodologia', label: 'Metodologia e Descritivos das Atividades' },
        { id: 'bibliografia', label: 'Bibliografia' },
        { id: 'aparelhos', label: 'Aparelhos' },
        { id: 'identificacao_local', label: 'Identificação do Local' },
        { id: 'agentes', label: 'Agentes Nocivos/Riscos' },
        { id: 'atividades_periculosas', label: 'Atividades e Operações Periculosas', hasDot: true },
        { id: 'conclusao', label: 'Conclusão' },
    ];


    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                <header className="mb-6">
                    <h1 className="text-3xl font-bold text-gray-900">Cadastrar LTIP</h1>
                </header>

                <form>
                    <div className="bg-white p-4 rounded-lg shadow-md mb-6 border-l-4 border-blue-600">
                        <div className="flex flex-wrap justify-between items-center text-sm gap-4">
                            <span><span className="font-semibold">Razão Social:</span> WAYSTER HENRIQUE CRUZ DE MELO</span>
                            <span><span className="font-semibold">Documento:</span> 59.413.555/0001-08</span>
                        </div>
                    </div>

                    <FormSection title="Empresa">
                        <div className="col-span-full grid grid-cols-1 md:grid-cols-2 gap-6">
                            <div>
                                <label className="text-sm font-medium text-gray-600">Empresa *</label>
                                <InputWithActions value="WAYSTER HENRIQUE CRUZ DE MELO" actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-red-500 hover:bg-red-600 rounded-r-md"><Trash2 size={18}/></button></>} />
                            </div>
                            <div></div>
                            <div>
                                <label className="text-sm font-medium text-gray-600">Setores</label>
                                <InputWithActions value="SEGURANÇA DO TRABALHO" actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-red-500 hover:bg-red-600 rounded-r-md"><Trash2 size={18}/></button></>} />
                            </div>
                            <div>
                                <label className="text-sm font-medium text-gray-600">Funções</label>
                                <InputWithActions value="ASSISTENTE DE PRODUÇÃO" actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-red-500 hover:bg-red-600 rounded-r-md"><Trash2 size={18}/></button></>} />
                            </div>
                        </div>
                    </FormSection>

                    <FormSection title="Data/Horário do Levantamento de Dados">
                        <div className="col-span-full grid grid-cols-1 md:grid-cols-3 gap-6">
                            <div><label className="text-sm font-medium text-gray-600">Data do Levantamento *</label><input type="text" defaultValue="02/06/2025" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/></div>
                            <div><label className="text-sm font-medium text-gray-600">Hora Inicial *</label><div className="relative"><input type="text" defaultValue="23:11" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/><Clock size={16} className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400"/></div></div>
                            <div><label className="text-sm font-medium text-gray-600">Hora Final *</label><div className="relative"><input type="text" defaultValue="23:17" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/><Clock size={16} className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400"/></div></div>
                        </div>
                    </FormSection>

                    <FormSection title="Sobre o documento">
                        <div className="col-span-full grid grid-cols-1 md:grid-cols-2 gap-6">
                            <div><label className="text-sm font-medium text-gray-600">Responsável Técnico/Elaborador *</label><InputWithActions value="MEDICO TESTE WAYSTER" actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-blue-600 hover:bg-blue-700 rounded-r-md"><Plus size={18}/></button></>} /></div>
                            <div><label className="text-sm font-medium text-gray-600">Demais Elaboradores</label><InputWithActions actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-blue-600 hover:bg-blue-700 rounded-r-md"><Plus size={18}/></button></>} /></div>
                        </div>
                        <div className="col-span-full grid grid-cols-1 md:grid-cols-4 gap-6">
                            <div className="md:col-span-2"><label className="text-sm font-medium text-gray-600">Responsável pela empresa *</label><div className="relative"><input type="text" defaultValue="wayster" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/><Check size={16} className="absolute right-3 top-1/2 -translate-y-1/2 text-green-500"/></div></div>
                            <div><label className="text-sm font-medium text-gray-600">Início da validade *</label><input type="text" defaultValue="07/06/2025" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/></div>
                            <div><label className="text-sm font-medium text-gray-600">Próxima revisão *</label><input type="text" defaultValue="17/06/2025" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/></div>
                            <div className="md:col-span-2"><label className="text-sm font-medium text-gray-600">Alerta de validade do laudo *</label><select className="w-full mt-1 py-2 px-3 border border-gray-300 rounded-md bg-white"><option>60 dias antes do vencimento</option></select></div>
                        </div>
                    </FormSection>

                    <div className="bg-white rounded-lg shadow-md">
                        <h3 className="text-xl font-semibold text-gray-800 p-6">Dados do LTIP</h3>
                        <div className="border-y border-gray-200 overflow-x-auto">
                            <nav className="flex space-x-2 px-4">
                                {tabLabels.map(tab => (
                                    <TabButton key={tab.id} label={tab.label} isActive={activeTab === tab.id} onClick={() => setActiveTab(tab.id)} hasDot={tab.hasDot} />
                                ))}
                            </nav>
                        </div>
                        {/* A chave da correção está aqui: ao renderizar o conteúdo, usamos uma chave única (o id da aba) para
                            garantir que o React substitua completamente o componente ao invés de apenas atualizá-lo. */}
                        <div className="p-6" key={activeTab}>
                            {tabContents[activeTab]}
                        </div>
                    </div>

                    <div className="flex flex-wrap justify-end gap-4 mt-8">
                        <button type="button" className="bg-red-600 text-white px-8 py-2.5 rounded-md font-semibold hover:bg-red-700 transition-colors">Cancelar</button>
                        <button type="submit" className="bg-green-600 text-white px-8 py-2.5 rounded-md font-semibold hover:bg-green-700 transition-colors">Salvar</button>
                    </div>
                </form>
            </div>
        </div>
    );
}
