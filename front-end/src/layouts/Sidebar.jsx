import { Link, useLocation } from 'react-router-dom';
import { LogOut, Settings} from 'lucide-react';
import {DashboardIcon, FileTextIcon, UsersIcon} from "../components/Icons.jsx";

function Sidebar() {
    const location = useLocation();
    const isActive = (path) => location.pathname === path;

    const menuItems = [
        { category: 'Principal' },
        { name: 'Cadastros', path: '/', icon: <DashboardIcon /> },
        { name: 'Check-Lists', path: '/checklists', icon: <FileTextIcon /> },
        { name: 'Colaboradores', path: '/colaboradores', icon: <UsersIcon /> },
    ];

    return (
        <aside className="w-[280px] h-screen bg-gradient-to-br from-emerald-700 to-emerald-900 fixed left-0 top-0 text-white shadow-xl z-10 flex flex-col">
            <div className="sidebar-header p-5 flex items-center border-b border-white/10">
                <div className="brand-logo-icon w-10 h-10 mr-4 flex items-center justify-center bg-white/10 rounded-xl p-2 backdrop-blur-sm">
                    <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M20 7L12 3L4 7M20 7L12 11M20 7V17L12 21M12 11L4 7M12 11V21M4 7V17L12 21" stroke="#FFFFFF" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/></svg>
                </div>
                <div className="brand-name flex flex-col">
                    <h2 className="text-lg font-bold tracking-wider m-0 text-white">OCUPACIONAL</h2>
                    <span className="text-xs opacity-80 mt-0.5">CHECK-LIST</span>
                </div>
            </div>

            <div className="sidebar-user p-5 flex items-center border-b border-white/10">
                <div className="user-avatar w-10 h-10 rounded-full bg-white text-emerald-700 font-bold flex items-center justify-center mr-3 shadow-inner">
                    <span className="font-bold">M</span>
                </div>
                <div className="user-info flex flex-col">
                    <span className="text-sm font-semibold">Marina</span>
                    <span className="text-xs opacity-80">Administrador</span>
                </div>
            </div>

            <nav className="sidebar-menu py-5 flex-grow overflow-y-auto">
                {menuItems.map((item, index) => (
                    item.category ? (
                        <div key={index} className="text-xs opacity-80 my-3 mx-5 uppercase tracking-wider">{item.category}</div>
                    ) : (
                        <Link
                            key={index}
                            to={item.path}
                            className={`menu-item py-3 px-5 flex items-center transition-all relative
                            text-white/90 hover:bg-white/10 group ${isActive(item.path) ? 'bg-white/15 text-white font-medium' : ''}`}
                        >
                            {isActive(item.path) && <span className="absolute left-0 top-0 w-1 h-full bg-white rounded-r"></span>}
                            <div className={`menu-icon w-5 h-5 mr-3 flex items-center justify-center transition-transform duration-300 ${isActive(item.path) ? 'text-white scale-110' : 'text-white/70 group-hover:text-white group-hover:scale-110'}`}>
                                {item.icon}
                            </div>
                            <span className="menu-text text-sm">{item.name}</span>
                            <div className="ml-auto opacity-0 group-hover:opacity-100 transition-opacity">
                                <svg xmlns="http://www.w3.org/2000/svg" className="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                                    <path fillRule="evenodd" d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z" clipRule="evenodd" />
                                </svg>
                            </div>
                        </Link>
                    )
                ))}
            </nav>

            <div className="sidebar-footer p-5 border-t border-white/10 flex items-center justify-between">
                <button className="settings-btn bg-white/10 hover:bg-white/20 w-9 h-9 rounded-lg flex items-center justify-center transition-all backdrop-blur-sm">
                    <Settings size={18} />
                </button>
                <button className="logout-btn bg-white/10 hover:bg-white/20 w-9 h-9 rounded-lg flex items-center justify-center transition-all backdrop-blur-sm">
                    <LogOut size={18} />
                </button>
            </div>
        </aside>
    );
}

export default Sidebar;