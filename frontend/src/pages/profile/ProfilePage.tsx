import React, { useState } from 'react';
import { ProfileSidebar } from '../../components/profile/ProfileSidebar';
import { ProfileEditForm } from '../../components/profile/ProfileEditForm';
import { PasswordChangeForm } from '../../components/profile/PasswordChangeForm';
import './ProfilePage.css';
import { TopBar } from '../../components/TopBar';

export function ProfilePage() {
  const [view, setView] = useState<'profile'|'password'>('profile');

  return (
    <div className="d-flex profile-page">
      <ProfileSidebar selected={view} onSelect={setView} />
      <div className="flex-grow-1 d-flex flex-column">
        <TopBar />
        <main className="flex-grow-1 p-4">
            {view === 'profile'
            ? <ProfileEditForm />
            : <PasswordChangeForm />
            }
      </main>
      </div>
    </div>
  );
}