import { Menu } from 'lucide-react';

function Header({ title, subtitle, children }) {
    return (
        <header className="page-header bg-white rounded-2xl p-5 mb-5 flex justify-between items-center shadow-sm">
            <div className="header-title flex flex-col">
                <h1 className="text-2xl font-bold text-dark">{title}</h1>
                {subtitle && <p className="text-sm text-gray-custom mt-1">{subtitle}</p>}
            </div>
            <div className="header-actions flex gap-2.5">
                {children}
            </div>
        </header>
    );
}

export const ActionButton = ({ children, primary = false, onClick }) => (
    <button onClick={onClick} className={`action-btn bg-light border-none rounded-lg py-2.5 px-3 flex items-center gap-2 cursor-pointer transition-all hover:-translate-y-0.5 hover:shadow-md text-dark ${primary ? 'bg-primary text-white' : ''}`}>
        {children}
    </button>
);




export default Header;