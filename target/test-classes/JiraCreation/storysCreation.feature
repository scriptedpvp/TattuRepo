Feature: Jira story creation
Scenario Outline: Jira story post
Given the user creates jiraStroy by providing "<summary>","<description>", "<issueType>"
  When the user verify key, "<summary>", "<description>"
  Then the user get key, "<summary>","<description>"
Examples:
| summary              | description | issueType |
| Story creation test1 | test1 desc  | Story     |
| Story creation test2 | test2 desc  | Story     |
| Story creation test3 | test3 desc  | Story     |
| Story creation test4 | test4 desc  | Story     |
| Story creation test5 | test5 desc  | Story     |