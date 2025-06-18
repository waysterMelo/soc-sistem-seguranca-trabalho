import React, { useState } from 'react';
import {
    Search,
    Trash2,
    Plus,
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
    Image as ImageIcon
} from 'lucide-react';

// --- DADOS DE EXEMPLO ---
const agentesNocivosData = [
    { codigo: '01.03.002', descricao: 'Estireno (vinilbenzeno)', setor: 'TÉCNICO SEGURANÇA', funcao: 'TECNICO SEGURANÇA' },
    { codigo: '01.03.001', descricao: 'Benzeno e seus compostos tóxicos...', setor: 'TÉCNICO SEGURANÇA', funcao: 'TECNICO SEGURANÇA' },
    { codigo: '01.02.001', descricao: 'Asbestos (ou amianto)', setor: 'TÉCNICO SEGURANÇA', funcao: 'TECNICO SEGURANÇA' },
];
const profissionaisData = ['MEDICO TESTE WAYSTER'];
const empresasContratantesData = [{empresa: 'EMPRESA CONTRATANTE EXEMPLO LTDA', unidade: 'UNIDADE CENTRAL'}];


// --- COMPONENTES REUTILIZÁVEIS ---

// Secção do formulário com título
const FormSection = ({ title, children, className }) => (
    <div className={`bg-white p-6 rounded-lg shadow-md ${className}`}>
        {title && <h3 className="text-xl font-semibold text-gray-800 border-b border-gray-200 pb-4 mb-6">{title}</h3>}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
            {children}
        </div>
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
const RichTextEditor = ({ initialContent = '', rows = 6 }) => (
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
const TabButton = ({ label, isActive, onClick }) => (
    <button type="button" onClick={onClick} className={`px-4 py-3 -mb-px text-sm font-semibold whitespace-nowrap transition-colors border-b-2 ${isActive ? 'border-blue-600 text-blue-600' : 'border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700'}`}>
        {label}
    </button>
);


// --- SUB-COMPONENTES PARA O CONTEÚDO DAS ABAS ---

const TabCapa = () => <RichTextEditor />;
const TabProfissionais = () => (
    <div className="space-y-4">
        <div>
            <label className="text-sm font-medium text-gray-600">Profissionais responsáveis pelo LTCAT *</label>
            <InputWithActions placeholder="" actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-blue-600 hover:bg-blue-700 rounded-r-md"><Plus size={18}/></button></>} />
        </div>
        <div>
            <label className="text-sm font-medium text-gray-600">Profissionais Selecionados</label>
            <div className="mt-2 border rounded-md p-2 space-y-2">
                {profissionaisData.map((prof, index) => (
                    <div key={index} className="flex justify-between items-center bg-gray-100 p-2 rounded">
                        <span>{prof}</span>
                        <button type="button" className="text-red-500 hover:text-red-700"><Trash2 size={16}/></button>
                    </div>
                ))}
            </div>
        </div>
        <div>
            <label className="text-sm font-medium text-gray-600">Observações sobre os profissionais</label>
            <RichTextEditor rows={4}/>
        </div>
    </div>
);
const TabLaudoTecnico = () => {
    const [activeSubTab, setActiveSubTab] = useState('responsabilidade');
    const subTabs = [
        { id: 'responsabilidade', label: 'Responsabilidade Técnica' },
        { id: 'introducao', label: 'Introdução' },
        { id: 'objetivos', label: 'Objetivos' },
        { id: 'gerais', label: 'Considerações Gerais' },
        { id: 'criterios', label: 'Critérios de Avaliação' },
    ];

    const renderContent = () => {
        switch(activeSubTab) {
            case 'responsabilidade': return <RichTextEditor initialContent="O presente documento tem a responsabilidade técnica..."/>;
            case 'introducao': return <RichTextEditor initialContent="Laudo Técnico de Condições Ambientais de Trabalho (LTCAT) é um documento..."/>;
            case 'objetivos': return <RichTextEditor initialContent="O LTCAT tem por finalidade cumprir as exigências da legislação previdenciária..."/>;
            default: return <RichTextEditor />;
        }
    };

    return (
        <div>
            <div className="border-b border-gray-200 mb-4">
                <nav className="flex flex-wrap -mb-px">
                    {subTabs.map(tab => (
                        <TabButton key={tab.id} label={tab.label} isActive={activeSubTab === tab.id} onClick={() => setActiveSubTab(tab.id)} />
                    ))}
                </nav>
            </div>
            {renderContent()}
        </div>
    );
};
const TabEmpresasContratantes = () => (
    <div className="space-y-4">
        <div>
            <label className="text-sm font-medium text-gray-600">Pesquisar Empresa</label>
            <InputWithActions placeholder="Digite para pesquisar uma empresa..." actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-blue-600 hover:bg-blue-700 rounded-r-md"><Plus size={18}/></button></>} />
        </div>
        <div>
            <label className="text-sm font-medium text-gray-600">Pesquisar Unidade</label>
            <InputWithActions placeholder="Digite para pesquisar uma unidade..." actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-blue-600 hover:bg-blue-700 rounded-r-md"><Plus size={18}/></button></>} />
        </div>
        <div>
            <label className="text-sm font-medium text-gray-600">Empresas e Unidades Vinculadas</label>
            <div className="mt-2 border rounded-md p-2 space-y-2">
                {empresasContratantesData.map((item, index) => (
                    <div key={index} className="flex justify-between items-center bg-gray-100 p-2 rounded">
                        <div>
                            <p className="font-semibold">{item.empresa}</p>
                            <p className="text-xs text-gray-600">Unidade: {item.unidade}</p>
                        </div>
                        <button type="button" className="text-red-500 hover:text-red-700"><Trash2 size={16}/></button>
                    </div>
                ))}
            </div>
        </div>
    </div>
);


// --- COMPONENTE PRINCIPAL ---
export default function CadastrarLTCAT() {
    const [activeTab, setActiveTab] = useState('capa');

    const mainTabs = [
        { id: 'capa', label: 'Capa', component: <TabCapa /> },
        { id: 'profissionais', label: 'Profissionais', component: <TabProfissionais /> },
        { id: 'laudo', label: 'Laudo Técnico', component: <TabLaudoTecnico /> },
        { id: 'recomendacoes', label: 'Recomendações Técnicas/Aparelhagem', component: <RichTextEditor /> },
        { id: 'bibliografia', label: 'Bibliografia', component: <RichTextEditor /> },
        { id: 'funcoes', label: 'Funções', component: <RichTextEditor /> },
        { id: 'conclusao', label: 'Conclusão', component: <RichTextEditor /> },
        { id: 'contratantes', label: 'Empresas Contratantes', component: <TabEmpresasContratantes /> }
    ];

    return (
        <div className="bg-gray-50 min-h-screen p-4 sm:p-6 lg:p-8 font-sans">
            <div className="container mx-auto">
                <header className="mb-6">
                    <h1 className="text-3xl font-bold text-gray-900">Cadastrar LTCAT</h1>
                </header>

                <form>
                    <div className="bg-white p-4 rounded-lg shadow-md mb-6 border-l-4 border-blue-600">
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 text-sm">
                            <div><span className="font-semibold">Razão Social:</span> WAYSTER HENRIQUE CRUZ DE MELO</div>
                            <div><span className="font-semibold">Documento:</span> 59.413.555/0001-08</div>
                        </div>
                    </div>

                    <FormSection title="Informações Básicas">
                        <div className="col-span-2">
                            <label className="text-sm font-medium text-gray-600">Empresa *</label>
                            <InputWithActions value="WAYSTER HENRIQUE CRUZ DE MELO" actions={<><button type="button" className="p-2.5 text-white bg-green-500 hover:bg-green-600"><Search size={18}/></button><button type="button" className="p-2.5 text-white bg-red-500 hover:bg-red-600 rounded-r-md"><Trash2 size={18}/></button></>} />
                        </div>
                        <div className="col-span-2 flex items-end gap-2">
                            <button type="button" className="px-3 py-2 text-sm bg-green-100 text-green-800 rounded-full">Empresa</button>
                        </div>
                        <div><label className="text-sm font-medium text-gray-600">Data do Documento *</label><input type="text" defaultValue="02/06/2025" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/></div>
                        <div><label className="text-sm font-medium text-gray-600">Data de Vencimento *</label><input type="text" defaultValue="03/06/2025" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/></div>
                        <div><label className="text-sm font-medium text-gray-600">Alerta de validade do laudo *</label><input type="text" defaultValue="66 dias antes do vencimento" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/></div>
                        <div><label className="text-sm font-medium text-gray-600">Situação *</label><select className="w-full mt-1 py-2 px-3 border border-gray-300 rounded-md bg-white"><option>Ativo</option></select></div>
                        <div><label className="text-sm font-medium text-gray-600">Cidade *</label><input type="text" defaultValue="Matozinhos" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/></div>
                        <div><label className="text-sm font-medium text-gray-600">Estado *</label><select className="w-full mt-1 py-2 px-3 border border-gray-300 rounded-md bg-white"><option>Minas Gerais</option></select></div>
                        <div className="col-span-full"><label className="text-sm font-medium text-gray-600">Comentários (não será impresso no LTCAT)</label><input type="text" className="mt-1 w-full py-2 px-3 border border-gray-300 rounded-md"/></div>
                        <div className="col-span-full"><label className="text-sm font-medium text-gray-600">Condições Preliminares</label><RichTextEditor rows={3}/></div>
                    </FormSection>

                    <div className="bg-white rounded-lg shadow-md">
                        <div className="border-b border-gray-200 overflow-x-auto">
                            <nav className="flex space-x-2 px-4">
                                {mainTabs.map(tab => (
                                    <TabButton key={tab.id} label={tab.label} isActive={activeTab === tab.id} onClick={() => setActiveTab(tab.id)} />
                                ))}
                            </nav>
                        </div>
                        <div className="p-6">{mainTabs.find(tab => tab.id === activeTab)?.component}</div>
                    </div>

                    <div className="bg-white p-6 rounded-lg shadow-md mt-8">
                        <h4 className="font-semibold text-blue-800 mb-4 text-center border-t border-b py-2 bg-blue-50">Agentes Nocivos</h4>
                        <div className="flex justify-end mb-4">
                            <button type="button" className="flex items-center gap-2 text-sm bg-blue-600 text-white px-3 py-2 rounded-md hover:bg-blue-700"><Plus size={16}/> Novo Agente Nocivo</button>
                        </div>
                        <div className="overflow-x-auto">
                            <table className="min-w-full divide-y divide-gray-200 text-sm">
                                <thead className="bg-gray-50">
                                <tr>
                                    <th className="px-4 py-2 text-left font-medium text-gray-500 uppercase">Código</th>
                                    <th className="px-4 py-2 text-left font-medium text-gray-500 uppercase">Descrição</th>
                                    <th className="px-4 py-2 text-left font-medium text-gray-500 uppercase">Setor</th>
                                    <th className="px-4 py-2 text-left font-medium text-gray-500 uppercase">Função</th>
                                    <th className="px-4 py-2 text-left font-medium text-gray-500 uppercase">Ações</th>
                                </tr>
                                </thead>
                                <tbody className="bg-white divide-y divide-gray-200">
                                {agentesNocivosData.map((agente, index) => (
                                    <tr key={index}>
                                        <td className="px-4 py-3">{agente.codigo}</td>
                                        <td className="px-4 py-3">{agente.descricao}</td>
                                        <td className="px-4 py-3">{agente.setor}</td>
                                        <td className="px-4 py-3">{agente.funcao}</td>
                                        <td className="px-4 py-3"><button type="button" className="text-red-500 hover:text-red-700"><Trash2 size={16}/></button></td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
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
