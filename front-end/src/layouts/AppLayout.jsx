import {Outlet} from 'react-router-dom';
import Sidebar from './Sidebar';

function AppLayout() {
    return (
        <div className="bg-light-gray min-h-screen flex relative">
            <Sidebar />
            <main className="main-content ml-[280px] w-[calc(100%-280px)] p-5 transition-all">
                <Outlet />
            </main>
        </div>
    );
}

export default AppLayout;