export interface Issue {
  pkey: string;
  summary: string;
  description: string;
  projectId: number;
  workflow_id: number;
  issue_type_id: number;
  issue_status_id: number;
  creator: string;
  reporter: string;
  assignee: string;
  environment: string;
  watches: number;
  security: number;
  archived_by: string;
  archived: string;
  due_date: string;
  archived_date: string;
}
