import React, {createContext, useContext, useState} from 'react';
import {Link, useLocation} from 'react-router-dom';
import {
    Building,
    ChevronDown,
    LayoutDashboard,
    LogOut,
    Menu,
    PanelLeftClose,
    PanelLeftOpen,
    Settings,
    Shield,
    User as UserIcon,
    X
} from 'lucide-react';

// --- Contexto para a Sidebar ---
const SidebarContext = createContext();

// --- Dados dos Itens do Menu (fornecidos por você) ---
const menuItems = [
    { name: 'Dashboard', icon: <LayoutDashboard size={20} />, path: '/dashboard' },
    {
        name: 'Cadastros',
        icon: <Building size={20} />,
        path: '/cadastros',
        subItems: [
            { name: 'Empresas', path: '/cadastros/listar/empresas' },
            { name: 'Unidades Operacionais', path: '/cadastros/listar/unidades' },
            { name: 'Setores', path: '/cadastros/listar/setores' },
            { name: 'Funções', path: '/cadastros/listar/funcao' },
            { name: 'Funcionários', path: '/cadastros/listar/funcionarios' },
            { name: 'Prestadores de Serviços', path: '/cadastros/prestador-servico' },
            { name: 'EPI/EPC', path: '/cadastros/epi-epc' },
            { name: 'Aparelhos', path: '/cadastros/aparelhos' },
        ],
    },
    {
        name: 'Segurança do Trabalho',
        icon: <Shield size={20} />,
        path: '/seguranca',
        subItems: [
            { name: 'PGR', path: '/seguranca/pgr' },
            { name: 'LTCAT', path: '/seguranca/ltcat' },
            { name: 'LTIP', path: '/seguranca/ltip' },
            { name: 'CAT', path: '/seguranca/cat' },
            { name: 'Treinamentos', path: '/seguranca/treinamentos' },
            { name: 'Movimentação de EPI', path: '/seguranca/epi' },
            { name: 'Ordem de Serviço', path: '/seguranca/listar/ordem-servico' }
        ],
    }
];

// --- Componente Principal da Sidebar ---
export default function Sidebar() {
    // Estado para controlar a sidebar em telas grandes (expandida/recolhida)
    const [isExpanded, setIsExpanded] = useState(true);
    // Estado para controlar a sidebar em telas pequenas (aberta/fechada)
    const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

    return (
        <>
            {/* Botão de menu para telas móveis */}
            <div className="lg:hidden fixed top-0 left-0 right-0 bg-gray-900/50 backdrop-blur-sm z-20 p-2 flex justify-between items-center">
                <h1 className="text-lg font-bold text-white">METRA CLOUD</h1>
                <button onClick={() => setIsMobileMenuOpen(true)} className="text-white p-2">
                    <Menu size={24} />
                </button>
            </div>

            {/* Overlay para fechar o menu no mobile */}
            {isMobileMenuOpen && (
                <div
                    onClick={() => setIsMobileMenuOpen(false)}
                    className="fixed inset-0 bg-black/60 z-30 lg:hidden"
                    aria-hidden="true"
                ></div>
            )}

            {/* Container da Sidebar */}
            <aside className={`fixed top-0 left-0 h-screen flex flex-col bg-gray-800 text-white shadow-xl z-40 transition-all duration-300
                ${isExpanded ? "w-72" : "w-20"}
                ${isMobileMenuOpen ? "translate-x-0" : "-translate-x-full"}
                lg:translate-x-0
            `} style={{background: 'linear-gradient(to bottom, #0f172a, #1e293b)'}}>

                <SidebarContext.Provider value={{ isExpanded }}>
                    {/* Cabeçalho */}
                    <header className="p-4 pb-2 flex justify-between items-center">
                        <div className={`flex items-center gap-2 overflow-hidden transition-all ${isExpanded ? "w-52" : "w-0"}`}>
                            <svg width="28" height="28" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" className="flex-shrink-0 text-yellow-400">
                                <path d="M12 3V21" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round"/><path d="M3 12H21" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round"/>
                            </svg>
                            <span className="font-bold text-xl whitespace-nowrap">METRA<span className="text-yellow-400">CLOUD</span></span>
                        </div>
                        <button onClick={() => isMobileMenuOpen ? setIsMobileMenuOpen(false) : setIsExpanded(p => !p)} className="p-2 rounded-lg text-gray-400 hover:bg-gray-700">
                            {isMobileMenuOpen ? <X size={20} /> : (isExpanded ? <PanelLeftClose size={20} /> : <PanelLeftOpen size={20} />)}
                        </button>
                    </header>

                    {/* Perfil do Usuário */}
                    <div className="flex flex-col items-center p-4 border-b border-gray-700/50">
                        <UserIcon size={isExpanded ? 60 : 40} className="rounded-full bg-gray-900/50 p-2 text-gray-500 transition-all" />
                        <div className={`overflow-hidden text-center transition-all duration-300 ${isExpanded ? "max-h-40 opacity-100 mt-2" : "max-h-0 opacity-0"}`}>
                            <h4 className="font-semibold text-lg">ADMIN</h4>
                            <span className="text-xs text-gray-400">CLÍNICA - M.G.L</span>
                        </div>
                    </div>

                    {/* Menu Principal */}
                    <nav className="flex-1 px-3 py-2 overflow-y-auto">
                        <ul className="space-y-1">
                            {menuItems.map((item) => (
                                <SidebarMenuItem key={item.name} item={item} />
                            ))}
                        </ul>
                    </nav>

                    {/* Rodapé */}
                    <div className="border-t border-gray-700/50 p-3">
                        <div className="flex items-center justify-between">
                            <div className={`flex items-center gap-3 overflow-hidden transition-all ${isExpanded ? "w-52" : "w-0"}`}>
                                <Settings size={20} className="flex-shrink-0" />
                                <div className="leading-4">
                                    <h4 className="font-semibold">Config.</h4>
                                    <span className="text-xs text-gray-400">geral</span>
                                </div>
                            </div>
                            <button className="p-2 rounded-lg hover:bg-gray-700 text-red-500 hover:text-red-400"><LogOut size={20} /></button>
                        </div>
                    </div>
                </SidebarContext.Provider>
            </aside>
        </>
    );
}


// --- Componente para Itens de Menu (com lógica de submenu e responsividade) ---
function SidebarMenuItem({ item }) {
    const { isExpanded } = useContext(SidebarContext);
    const location = useLocation();

    // Um item pai está ativo se a URL atual começar com o seu caminho base
    const isParentActive = item.subItems && location.pathname.startsWith(item.path);
    const [isOpen, setIsOpen] = useState(isParentActive);

    // Lógica de Ativação do Link
    const isActive = location.pathname === item.path || isParentActive;

    const handleToggle = () => {
        if(item.subItems) setIsOpen(prev => !prev);
    };

    return (
        <li>
            <Link
                to={item.subItems ? '#' : item.path}
                onClick={handleToggle}
                className={`relative flex items-center justify-between py-2 px-3 my-1 rounded-md cursor-pointer
                    transition-colors group
                    ${ isActive
                    ? "bg-gradient-to-tr from-emerald-600 to-green-500 text-white shadow-lg"
                    : "hover:bg-gray-700 text-gray-300"
                }`
                }
            >
                <div className="flex items-center gap-3">
                    {React.cloneElement(item.icon, { size: 20 })}
                    <span className={`overflow-hidden transition-all ${isExpanded ? "w-36" : "w-0"}`}>{item.name}</span>
                </div>
                {item.subItems && isExpanded && <ChevronDown size={16} className={`transition-transform ${isOpen ? "rotate-180" : ""}`}/>}

                {!isExpanded && (
                    <div className={`
                        absolute left-full rounded-md px-2 py-1 ml-4
                        bg-emerald-800 text-white text-sm
                        invisible opacity-20 -translate-x-3 transition-all
                        group-hover:visible group-hover:opacity-100 group-hover:translate-x-0
                    `}>
                        {item.name}
                    </div>
                )}
            </Link>

            {isExpanded && item.subItems && (
                <div className={`overflow-hidden transition-all duration-300 ease-in-out ${isOpen ? 'max-h-[500px]' : 'max-h-0'}`}>
                    <ul className="pl-8 pt-2 pb-1 border-l-2 border-emerald-600/30 ml-4 space-y-1">
                        {item.subItems.map(subItem => (
                            <li key={subItem.path}>
                                <Link
                                    to={subItem.path}
                                    className={`flex items-center gap-2 p-2 rounded-md text-sm transition-colors
                                   ${ location.pathname === subItem.path ? 'text-white font-medium' : 'text-gray-400 hover:text-white'}`}
                                >
                                    {subItem.icon && React.cloneElement(subItem.icon, { size: 16 })}
                                    <span>{subItem.name}</span>
                                </Link>
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </li>
    );
}
