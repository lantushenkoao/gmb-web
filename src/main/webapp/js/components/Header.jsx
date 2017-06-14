import React, {Component} from 'react';
import {Link} from 'react-router';

class Header extends Component{
    constructor(props){
        super(props);
        this.state = {
            currentUser: null
        };
        this.logout = this.logout.bind(this);
    }
    logout(){
        const self = this;
        $.ajax({
            type:'POST',
            url:'logout'
        }).done(function(){
            self._logout(self);
        });
    }
    componentDidMount(){
        this.reload();
    }
    reload(){
        const self = this;
        $.ajax({
            type:'GET',
            url: '/api/user/current'
        }).done(function(data){
            self.setState({
                currentUser: data
            });
            if(self.props.userLoadedCallback){
                self.props.userLoadedCallback(data);
            }
        }).fail(function(xhr){
            if(self.props.logoutIfNoSession) {
                self._logout(self);
            }
        });
    }
    _logout(thiz){
        thiz.props.history.push('/login');
    }
    render(){

        if(!this.state.currentUser){
            return <div className="container">
                <div>Please login</div>
                <Link to={'/login'} >Login</Link>
            </div>;
        }

        var canManageUsers = false;

        if (-1 != $.inArray('ROLE_MANAGE_USERS', this.state.currentUser.roles)) {
            canManageUsers = true;
        }
        return (
            <div className="container">
                Welcome {this.state.currentUser.fullName}.
                <div className="row">
                    <div className="col-lg-2"><Link to={'/'}>Home</Link></div>
                    <div className="col-lg-2"><Link to="/data">Bar Chart</Link></div>
                    <div className="col-lg-2"><Link to="/myaccount">My Account</Link></div>
                    {canManageUsers ? <div className="col-lg-2"><Link to="/users/list">List Users</Link></div> : null}
                    <div className="col-lg-2"><a href="#" onClick={this.logout}>Log out</a></div>
                </div>
            </div>
        );
    }
};

Header.defaultProps = {
    logoutIfNoSession: true
}

Header.propTypes = {
    userLoadedCallback: React.PropTypes.func,
    logoutIfNoSession: React.PropTypes.bool
}

module.exports = Header;