import React, { Component } from 'react';
import './Task.css';

export class Task extends Component {
  static displayName = Task.name;

  constructor(props) {
    super(props);
    this.state = { 
      tab: 'browse',
      tasks: [{
        id: 'asd',
        title: 'cxz'

      },{
        id: 'asd',
        title: 'cxz'

      },{
        id: 'asd',
        title: 'cxz'

      }]
  
  };
    this.handleTabClick = this.handleTabClick.bind(this);
  }

  handleTabClick = (tab) => {
    this.setState({
      tab: tab
    });
  }


  render() {
    return (
      <div>
        <nav>
          <button onClick={() => this.handleTabClick('browse')}>Browse Task</button>
          <button onClick={() => this.handleTabClick('schedule')}>Schedule Task</button>
          <button onClick={() => this.handleTabClick('close')}>Close Task</button>
          <button onClick={() => this.handleTabClick('transfer')}>Transfer Task</button>
          <button onClick={() => this.handleTabClick('assign')}>Assign Task</button>
          <button onClick={() => this.handleTabClick('closeConversation')}>Close Conversation</button>
        </nav>
        <Content activeTab={this.state.tab} />
      </div>
    );
  }

}

function TaskList({ tasks }) {
  return (
    <ul>
      {tasks.map(task => (
        <li key={task.id}>{task.title}</li>
      ))}
    </ul>
  );
}


function BrowseTask() {
  var tasks =[{
    id: 'asd',
    title: 'casdaxz'

  },{
    id: 'asd',
    title: 'casdasdaxz'

  },{
    id: 'asd',
    title: 'casdasdaxz'

  }]
  

  return (
    <div>
      <TaskList tasks={tasks} />
    </div>
  );
}

function Content({ activeTab ,tasks}) {
  switch (activeTab) {
    case 'browse':
      return (
        <div>
          <h1>Browse Task</h1>
          {/* TODO: add browse task UI */}
          <BrowseTask/>
        </div>
      );
    case 'schedule':
      return (
        <div>
          <h1>Schedule Task</h1>
          {/* TODO: add schedule task UI */}
        </div>
      );
    case 'close':
      return (
        <div>
          <h1>Close Task</h1>
          {/* TODO: add close task UI */}
        </div>
      );
    case 'transfer':
      return (
        <div>
          <h1>Transfer Task</h1>
          {/* TODO: add transfer task UI */}
        </div>
      );
    case 'assign':
      return (
        <div>
          <h1>Assign Task</h1>
          {/* TODO: add assign task UI */}
        </div>
      );
    case 'closeConversation':
      return (
        <div>
          <h1>Close Conversation</h1>
          {/* TODO: add close conversation UI */}
        </div>
      );
    default:
      return (
        <div>
          <h1>Browse Task</h1>
          {/* TODO: add default UI */}
        </div>
      );
  }
}
