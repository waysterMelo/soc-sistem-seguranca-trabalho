import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import {
    LayoutDashboard,
    ChevronDown,
    Building,
    Building2,
    Briefcase,
    User as UserIcon, // Renomeado para evitar conflito
    Users,
    Truck,
    Shield,
    Download,
    Radio,
    LogOut,
    Settings,
    PanelLeftClose
} from 'lucide-react';


// --- Componente de UI para o Logo ---
const MetraCloudLogo = () => (
    <div className="flex items-center text-xl font-bold text-white tracking-wider">
        <svg width="28" height="28" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" className="mr-2 text-yellow-400">
            <path d="M12 3V21" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round"/>
            <path d="M3 12H21" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round"/>
        </svg>
        <span>METRA</span>
        <span className="text-yellow-400">CLOUD</span>
    </div>
);


// Dados para os itens do menu, incluindo submenus (do seu código)
const menuItems = [
    { name: 'Dashboard', icon: <LayoutDashboard size={20} />, path: '/dashboard' },
    {
        name: 'Cadastros',
        icon: <Building size={20} />,
        path: '#', // Usado para indicar um submenu
        subItems: [
            { name: 'Empresas', path: 'cadastros/listar/empresas', icon: <Building2 size={18} /> },
            { name: 'Unidades Operacionais', path: 'cadastros/listar/unidades', icon: <Building2 size={18} /> },
            { name: 'Setores', path: '/setores', icon: <Briefcase size={18} /> },
            { name: 'Funções', path: '/funcoes', icon: <UserIcon size={18} /> },
            { name: 'Funcionários', path: '/funcionarios', icon: <Users size={18} /> },
            { name: 'Prestadores de Serviços', path: '/prestadores', icon: <Truck size={18} /> },
            { name: 'EPI/EPC', path: '/epi-epc', icon: <Shield size={18} /> },
            { name: 'Importações', path: '/importacoes', icon: <Download size={18} /> },
            { name: 'Aparelhos', path: '/aparelhos', icon: <Radio size={18} /> },
        ],
    }
    // Adicione outros itens de menu principais aqui
];

// Componente para um item de menu individual (do seu código)
const SidebarMenuItem = ({ item, activePath }) => {
    const location = useLocation();
    const [isOpen, setIsOpen] = useState(location.pathname.startsWith(item.path) && item.path !== '#');

    const isSubActive = item.subItems && item.subItems.some(sub => sub.path === activePath);
    const isActive = activePath === item.path || isSubActive;

    const handleToggle = () => {
        if (item.subItems) {
            setIsOpen(!isOpen);
        }
    };

    if (!item.subItems) {
        return (
            <Link
                to={item.path}
                className={`flex items-center p-3 my-1 rounded-lg transition-colors duration-200 ${
                    isActive ? 'bg-emerald-600 text-white shadow-md' : 'text-gray-300 hover:bg-emerald-700/50 hover:text-white'
                }`}
            >
                {item.icon}
                <span className="ml-4 font-medium">{item.name}</span>
            </Link>
        );
    }

    return (
        <div>
            <button
                onClick={handleToggle}
                className={`w-full flex items-center justify-between p-3 my-1 rounded-lg transition-colors duration-200 ${
                    isActive ? 'bg-emerald-600 text-white' : 'text-gray-300 hover:bg-emerald-700/50 hover:text-white'
                }`}
            >
                <div className="flex items-center">
                    {item.icon}
                    <span className="ml-4 font-medium">{item.name}</span>
                </div>
                <ChevronDown
                    size={20}
                    className={`transition-transform duration-300 ${isOpen ? 'rotate-180' : ''}`}
                />
            </button>
            <div
                className={`overflow-hidden transition-all duration-300 ease-in-out ${isOpen ? 'max-h-96' : 'max-h-0'}`}
            >
                <div className="pl-8 pt-2 space-y-1 border-l-2 border-emerald-600/50 ml-5">
                    {item.subItems.map((subItem, index) => (
                        <Link
                            key={index}
                            to={subItem.path}
                            className={`flex items-center p-2 rounded-md text-sm transition-colors duration-200 ${
                                activePath === subItem.path
                                    ? 'text-white font-semibold'
                                    : 'text-gray-400 hover:text-white hover:bg-emerald-700/40'
                            }`}
                        >
                            {subItem.icon}
                            <span className="ml-3">{subItem.name}</span>
                        </Link>
                    ))}
                </div>
            </div>
        </div>
    );
};


export default function Sidebar() {
    const location = useLocation();

    return (
        <aside className="w-72 h-screen bg-gray-800 text-white flex flex-col fixed shadow-lg" style={{background: 'linear-gradient(to bottom, #0f172a, #1e293b)'}}>

            {/* Cabeçalho com Logo e Botão (do novo design) */}
            <div className="flex items-center justify-between p-4 border-b border-gray-700/50">
                <MetraCloudLogo />
                <button className="p-2 rounded-md text-gray-400 hover:bg-gray-700 hover:text-white">
                    <PanelLeftClose size={20} />
                </button>
            </div>

            {/* Perfil do Usuário (do novo design) */}
            <div className="p-5 flex flex-col items-center text-center border-b border-gray-700/50">
                <div className="w-24 h-24 rounded-full bg-gray-900/50 flex items-center justify-center mb-4">
                    <UserIcon size={50} className="text-gray-500" />
                </div>
                <h2 className="font-semibold text-lg text-white">ADMIN</h2>
                <button className="flex items-center text-sm text-gray-400 hover:text-white">
                    <span>CLÍNICA - MARINA GARCIA LOPES...</span>
                    <ChevronDown size={16} className="ml-1" />
                </button>
            </div>

            {/* Menu Principal (do seu código) */}
            <nav className="flex-grow p-4 space-y-2 overflow-y-auto">
                {menuItems.map((item, index) => (
                    <SidebarMenuItem key={index} item={item} activePath={location.pathname} />
                ))}
            </nav>

            {/* Rodapé com Ações (do seu código) */}
            <div className="p-4 border-t border-gray-700">
                <button className="w-full flex items-center justify-center p-3 rounded-lg text-gray-300 hover:bg-emerald-700/50 hover:text-white transition-colors duration-200">
                    <Settings size={20} />
                    <span className="ml-3 font-medium">Configurações</span>
                </button>
                <button className="w-full flex items-center justify-center mt-2 p-3 rounded-lg text-gray-300 hover:bg-red-700/50 hover:text-white transition-colors duration-200">
                    <LogOut size={20} />
                    <span className="ml-3 font-medium">Sair</span>
                </button>
            </div>
        </aside>
    );
}
